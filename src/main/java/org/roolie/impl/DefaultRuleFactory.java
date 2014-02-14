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

package org.roolie.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.roolie.Rule;
import org.roolie.RuleFactory;
import org.roolie.config.elmt.ListItemElmt;
import org.roolie.config.elmt.PropertyElmt;
import org.roolie.config.elmt.RuleImplElmt;
import org.roolie.factory.InstanceFactory;
import org.roolie.util.RUtil;

public class DefaultRuleFactory implements RuleFactory
{
  protected final Map<String, Rule> ruleInstances =
    new ConcurrentHashMap<String, Rule>();

  protected final InstanceFactory<Rule> ruleInstanceFactory =
    new InstanceFactory<Rule>();

  public Rule getRule(RuleImplElmt ruleImplConfig)
  {
    final String ruleName = ruleImplConfig.getRuleName();
    Rule rule = ruleInstances.get(ruleName);
    if (null == rule)
    {
      final String ruleClass = ruleImplConfig.getRuleClass();
      rule = ruleInstanceFactory.newInstance(ruleClass);
      setProperties(rule, ruleImplConfig);
      ruleInstances.put(ruleName, rule);
    }
    return rule;
  }

  protected void setProperties(Rule rule, RuleImplElmt ruleImplElmt)
  {
    List<PropertyElmt> propertyElmts = ruleImplElmt.getPropertyElmts();

    if (RUtil.isNullOrEmpty(propertyElmts))
    {
      return;
    }

    for (PropertyElmt propertyElmt : propertyElmts)
    {
      if (propertyElmt.isListProperty())
      {
        setListProperty(rule, propertyElmt);
      }
      else
      {
        setProperty(rule, propertyElmt);
      }
    }
  }

  protected void setListProperty(Rule rule, PropertyElmt propertyElmt)
  {
    List<String> stringList = new LinkedList<String>();
    List<ListItemElmt> listItemElmts = propertyElmt.getListItems();
    for (ListItemElmt listItemElmt : listItemElmts)
    {
      final String value = listItemElmt.getValue();
      stringList.add(value);
    }
    final String propertyName = propertyElmt.getName();
    RUtil.setProperty(rule, propertyName, stringList);
  }

  protected void setProperty(Rule rule, PropertyElmt propertyElmt)
  {
    final String propertyName = propertyElmt.getName();
    final String propertyVal = propertyElmt.getValue();
    RUtil.setProperty(rule, propertyName, propertyVal);
  }

}
