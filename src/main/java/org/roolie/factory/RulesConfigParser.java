///////////////////////////////////////////////////////////////////////////////
//  Copyright 2010 Ryan Kennedy <rallyredevo AT users DOT sourceforge DOT net>
//
//  This file is part of Roolie.
//
//  Roolie is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or any later
//  version.
//
//  Roolie is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public License
//  along with Roolie.  If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////
package org.roolie.factory;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.xpath.XPathExpressionException;
import org.roolie.config.RulesConfig;
import org.roolie.config.elmt.ListItemElmt;
import org.roolie.config.elmt.PropertyElmt;
import org.roolie.config.elmt.RoolieConfigElmt;
import org.roolie.config.elmt.RuleDefElmt;
import org.roolie.config.elmt.RuleDefinitionsElmt;
import org.roolie.config.elmt.RuleElmt;
import org.roolie.config.elmt.RuleImplElmt;
import org.roolie.config.elmt.RuleImplementationsElmt;
import org.roolie.constants.RoolieConst.XML;
import org.roolie.util.RUtil;
import org.w3c.dom.Node;

public class RulesConfigParser
{

  protected static RulesConfigParser instance = new RulesConfigParser();

  protected RulesConfigParser()
  {
    super();
  }

  public static RulesConfigParser getInstance()
  {
    return instance;
  }

  public RulesConfig parseRulesConfig(Node configNode)
    throws XPathExpressionException
  {
    // Validate the document
    validateDocument(configNode);

    // Create a new RulesConfig
    RulesConfig rulesConfig = new RulesConfig();

    // Get the root node
    Node roolieRulesConfigNode = RUtil.findRequiredSingleNode(configNode,
      XML.NODE.ROOLIE_RULES_CONFIG);

    // Init roolie-config
    Node roolieConfigNode =
      RUtil.findSingleNode(roolieRulesConfigNode, XML.NODE.ROOLIE_CONFIG);
    initRoolieConfig(roolieConfigNode, rulesConfig);

    // Init rule-implementations
    Node ruleImplementationsNode =
      RUtil.findRequiredSingleNode(roolieRulesConfigNode,
      XML.NODE.RULE_IMPLEMENTATIONS);
    initRuleImplementations(ruleImplementationsNode, rulesConfig);

    // Init rule-definitions
    Node ruleDefinitionsNode =
      RUtil.findRequiredSingleNode(roolieRulesConfigNode,
      XML.NODE.RULE_DEFINITIONS);
    initRuleDefinitions(ruleDefinitionsNode, rulesConfig);

    // Post-process configuration
    postProcessRulesConfig(rulesConfig);

    return rulesConfig;
  }

  protected void initRoolieConfig(Node roolieConfigNode,
    RulesConfig rulesConfig) throws XPathExpressionException
  {
    // Create a new RoolieConfigElmt
    RoolieConfigElmt roolieConfigElmt = new RoolieConfigElmt();
    // init rule-factory-class property
    Node ruleFactoryClassNode = null;
    if (null != roolieConfigNode)
    {
      ruleFactoryClassNode = RUtil.getChild(roolieConfigNode,
        XML.NODE.RULE_FACTORY_CLASS);
    }
    if (null != ruleFactoryClassNode)
    {
      final String ruleFactoryClass = RUtil.getAttributeValue(
        ruleFactoryClassNode, XML.ATTRIB.CLASS);
      // Set the rule-factory-class property in the RoolieConfigElmt
      roolieConfigElmt.setRuleFactoryClass(ruleFactoryClass);
    }
    // init rule-evaluator-class
    Node ruleEvaluatorNode = null;
    if (null != roolieConfigNode)
    {
      ruleEvaluatorNode = RUtil.getChild(roolieConfigNode,
        XML.NODE.RULE_EVALUATOR_CLASS);
    }
    if (null != ruleEvaluatorNode)
    {
      final String ruleEvaluatorClass = RUtil.getAttributeValue(
        ruleEvaluatorNode, XML.ATTRIB.CLASS);
      // Set the ruule-evaluator-class property in the RoolieConfigElmt
      roolieConfigElmt.setRuleEvaluatorClass(ruleEvaluatorClass);
    }
    // Set the RoolieConfigElmt in rulesConfig
    rulesConfig.setRoolieConfigElmt(roolieConfigElmt);
  }

  protected void initRuleImplementations(Node ruleImplementationsNode,
    RulesConfig rulesConfig) throws XPathExpressionException
  {
    // Create a new RuleImplementationsElmt
    RuleImplementationsElmt ruleImplementationsElmt =
      new RuleImplementationsElmt();

    // Map to hold the RuleImpleElmt's
    Map<String, RuleImplElmt> ruleImplElmts =
      new HashMap<String, RuleImplElmt>();

    // Get the rule-impl nodes
    List<Node> ruleImplNodes = RUtil.getChildren(ruleImplementationsNode,
      XML.NODE.RULE_IMPL);

    for (Node ruleImplNode : ruleImplNodes)
    {
      RuleImplElmt ruleImplElmt = createRuleImplElmt(ruleImplNode);
      ruleImplElmts.put(ruleImplElmt.getRuleName(), ruleImplElmt);
    }

    // Set the ruleImplElmts in the ruleImplementationsElmt
    ruleImplementationsElmt.setRuleImplElmts(ruleImplElmts);

    // Set the RuleImplementationsElmt in rulesConfig
    rulesConfig.setRuleImplementationsElmt(ruleImplementationsElmt);
  }

  protected RuleImplElmt createRuleImplElmt(Node ruleImplNode) throws
    XPathExpressionException
  {
    // Create a new RuleImplElmt
    RuleImplElmt ruleImplElmt = new RuleImplElmt();

    // Set the rule name
    final String ruleName = RUtil.getRequiredAttributeValue(ruleImplNode,
      XML.ATTRIB.NAME);
    ruleImplElmt.setRuleName(ruleName);

    // Set the rule class
    final String ruleClass = RUtil.getRequiredAttributeValue(ruleImplNode,
      XML.ATTRIB.CLASS);
    ruleImplElmt.setRuleClass(ruleClass);

    // Set properties (if any)
    List<PropertyElmt> propertyElmts = createPropertyElmts(ruleImplNode);
    ruleImplElmt.setPropertyElmts(propertyElmts);

    return ruleImplElmt;
  }

  protected List<PropertyElmt> createPropertyElmts(Node ruleImplNode)
    throws XPathExpressionException
  {
    List<PropertyElmt> propertyElmts = null;
    List<Node> propertyNodes =
      RUtil.getChildren(ruleImplNode, XML.NODE.PROPERTY);
    if (RUtil.isNullOrEmpty(propertyNodes))
    {
      return propertyElmts;
    }
    propertyElmts = new LinkedList<PropertyElmt>();
    for (Node propertyNode : propertyNodes)
    {
      // Create a new PropertyElmt
      PropertyElmt propertyElmt = new PropertyElmt();

      // Set name
      final String name = RUtil.getRequiredAttributeValue(propertyNode,
        XML.ATTRIB.NAME);
      propertyElmt.setName(name);

      // Set value
      final String value = RUtil.getAttributeValue(propertyNode,
        XML.ATTRIB.VALUE);
      propertyElmt.setValue(value);

      // Set list items
      final List<ListItemElmt> listItems = createListItemElmts(propertyNode);
      propertyElmt.setListItems(listItems);

      // Make sure there is either listItems or a value, but not both
      RUtil.assertExclusiveOR((null == value), (null == listItems), "Either "
        + XML.ATTRIB.VALUE + " or " + XML.NODE.LIST
        + " are required, but not both");

      // Add propertyElmt to list
      propertyElmts.add(propertyElmt);
    }
    return propertyElmts;
  }

  protected List<ListItemElmt> createListItemElmts(Node propertyNode) throws
    XPathExpressionException
  {
    List<ListItemElmt> listItemElmts = null;
    Node listNode = RUtil.getChild(propertyNode, XML.NODE.LIST);
    if (null != listNode)
    {
      listItemElmts = new LinkedList<ListItemElmt>();
      List<Node> listItemNodes = RUtil.getChildren(listNode, XML.NODE.LIST_ITEM);
      for (Node listItemNode : listItemNodes)
      {
        ListItemElmt listItemElmt = createListItemElmt(listItemNode);
        listItemElmts.add(listItemElmt);
      }
    }
    return listItemElmts;
  }

  protected ListItemElmt createListItemElmt(Node listItemNode) throws
    XPathExpressionException
  {
    ListItemElmt listItemElmt = new ListItemElmt();
    final String value = RUtil.getRequiredAttributeValue(listItemNode,
      XML.ATTRIB.VALUE);
    listItemElmt.setValue(value);
    return listItemElmt;
  }

  protected void initRuleDefinitions(Node ruleDefinitionsNode,
    RulesConfig rulesConfig) throws XPathExpressionException
  {
    RuleDefinitionsElmt ruleDefinitionsElmt = new RuleDefinitionsElmt();
    List<RuleDefElmt> ruleDefElmts = createRuleDefElmts(ruleDefinitionsNode);
    Map<String, RuleDefElmt> ruleDefElmtsMap =
      new HashMap<String, RuleDefElmt>();
    for (RuleDefElmt ruleDefElmt : ruleDefElmts)
    {
      ruleDefElmtsMap.put(ruleDefElmt.getName(), ruleDefElmt);
    }
    ruleDefinitionsElmt.setRuleDefElmts(ruleDefElmtsMap);
    rulesConfig.setRuleDefinitionElmts(ruleDefinitionsElmt);
  }

  protected List<RuleDefElmt> createRuleDefElmts(Node ruleDefinitionsNode)
    throws
    XPathExpressionException
  {
    List<Node> ruleDefNodes = RUtil.getRequiredChildren(ruleDefinitionsNode,
      XML.NODE.RULE_DEF);
    List<RuleDefElmt> ruleDefElmts = new LinkedList<RuleDefElmt>();
    for (Node ruleDefNode : ruleDefNodes)
    {
      RuleDefElmt ruleDefElmt = createRuleDefElmt(ruleDefNode);
      ruleDefElmts.add(ruleDefElmt);
    }
    return ruleDefElmts;
  }

  protected RuleDefElmt createRuleDefElmt(Node ruleDefNode) throws
    XPathExpressionException
  {
    // Create RuleDefElmt
    RuleDefElmt ruleDefElmt = new RuleDefElmt();

    // name
    final String ruleDefName = RUtil.getRequiredAttributeValue(ruleDefNode,
      XML.ATTRIB.NAME);
    ruleDefElmt.setName(ruleDefName);

    // RuleElmt's
    List<RuleElmt> ruleElmts = createRuleElmts(ruleDefNode);
    ruleDefElmt.setRuleElmts(ruleElmts);
    return ruleDefElmt;
  }

  protected List<RuleElmt> createRuleElmts(Node ruleDefNode) throws
    XPathExpressionException
  {
    // Get the rule nodes
    List<Node> ruleNodes = RUtil.getRequiredChildren(ruleDefNode, XML.NODE.RULE);
    List<RuleElmt> ruleElmts = new LinkedList<RuleElmt>();
    for (Node ruleNode : ruleNodes)
    {
      RuleElmt ruleElmt = createRuleElmt(ruleNode);
      ruleElmts.add(ruleElmt);
    }
    return ruleElmts;
  }

  private RuleElmt createRuleElmt(Node ruleNode) throws XPathExpressionException
  {
    RuleElmt ruleElmt = new RuleElmt();

    // name
    final String name = RUtil.getRequiredAttributeValue(ruleNode,
      XML.ATTRIB.NAME);
    ruleElmt.setName(name);

    // or-next-rule
    final String sOrNextRule = RUtil.getAttributeValue(ruleNode,
      XML.ATTRIB.OR_NEXT_RULE);
    final boolean orNextRule = RUtil.parseBoolean(sOrNextRule);
    ruleElmt.setOrNextRule(orNextRule);

    // inverse
    final String sInverse =
      RUtil.getAttributeValue(ruleNode, XML.ATTRIB.INVERSE);
    final boolean inverse = RUtil.parseBoolean(sInverse);
    ruleElmt.setInverse(inverse);

    return ruleElmt;
  }

  protected void postProcessRulesConfig(RulesConfig rulesConfig)
  {
    // Add rules for rule defs
    initRulesForRuleDefs(rulesConfig);

    // Initialize the references
    initReferences(rulesConfig);
  }

  protected void initRulesForRuleDefs(RulesConfig rulesConfig)
  {
    Map<String, RuleImplElmt> ruleImplElmts =
      rulesConfig.getRuleImplementationsElmt().getRuleImplElmts();
    for (RuleImplElmt ruleImplElmt : ruleImplElmts.values())
    {
      RuleDefElmt ruleDefElmt = new RuleDefElmt();
      // name
      final String name = ruleImplElmt.getRuleName();
      ruleDefElmt.setName(name);

      // rules
      List<RuleElmt> ruleElmts = new LinkedList<RuleElmt>();
      RuleElmt ruleElmt = new RuleElmt();
      ruleElmt.setName(name);
      ruleElmts.add(ruleElmt);
      ruleDefElmt.setRuleElmts(ruleElmts);

      // add rule-def
      rulesConfig.getRuleDefinitionElmts().getRuleDefElmts().put(name,
        ruleDefElmt);
    }
  }

  protected void initReferences(RulesConfig rulesConfig)
  {
    // Init rule to rule-impl references (for speedy processing of rules)
    Collection<RuleDefElmt> ruleDefElmts = rulesConfig.getRuleDefinitionElmts().
      getRuleDefElmts().values();
    for (RuleDefElmt ruleDefElmt : ruleDefElmts)
    {
      List<RuleElmt> ruleElmts = ruleDefElmt.getRuleElmts();
      for (RuleElmt ruleElmt : ruleElmts)
      {
        final String ruleImplName = ruleElmt.getName();
        RuleImplElmt ruleImplElmt = findRuleImplElmt(rulesConfig, ruleImplName);
        RUtil.assertNotNull(ruleImplElmt, "There is no rule-impl with name "
          + ruleImplName);
        ruleElmt.setRuleImplElmtRef(ruleImplElmt);
      }
    }

  }

  protected RuleImplElmt findRuleImplElmt(RulesConfig rulesConfig, String name)
  {
    RuleImplElmt ruleImplElmt = rulesConfig.getRuleImplementationsElmt().
      getRuleImplElmts().get(name);
    return ruleImplElmt;
  }

  /*
   * Validates conditions in the config document not checked when parsing.
   */
  protected void validateDocument(Node node)
  {
    try
    {
      Set<String> names = new HashSet<String>();

      // Check unique names for rule-def and rule-impl nodes...

      // rule-def
      List<Node> ruleDefNodes =
        RUtil.findAllNodes(node,
        XML.NODE.RULE_DEF);

      for (Node ruleDefNode : ruleDefNodes)
      {
        final String name =
          RUtil.getRequiredAttributeValue(ruleDefNode,
          XML.ATTRIB.NAME);

        RUtil.assertFalse(names.contains(name), "Attribute " + XML.ATTRIB.NAME
          + " must be unique among all " + XML.NODE.RULE_DEF + " and "
          + XML.NODE.RULE_IMPL + " nodes");

        names.add(name);
      }

      // rule-impl
      List<Node> ruleImplNodes =
        RUtil.findAllNodes(node,
        XML.NODE.RULE_IMPL);

      for (Node ruleImpleNode : ruleImplNodes)
      {
        final String name =
          RUtil.getRequiredAttributeValue(ruleImpleNode,
          XML.ATTRIB.NAME);

        RUtil.assertFalse(names.contains(name), "Attribute " + XML.ATTRIB.NAME
          + " must be unique among all " + XML.NODE.RULE_DEF + " and "
          + XML.NODE.RULE_IMPL + " nodes");

        names.add(name);
      }
    }
    catch (Throwable t)
    {
      throw new RuntimeException(t);
    }
  }
}
