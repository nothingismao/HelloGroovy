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

package org.roolie.constants;

import org.roolie.impl.DefaultRuleEvaluator;
import org.roolie.impl.DefaultRuleFactory;

public interface RoolieConst
{
  public static class CLASS
  {
    public static final String DEFAULT_RULE_FACTORY = 
      DefaultRuleFactory.class.getName();
    public static final String DEFAULT_RULE_EVALUATOR =
      DefaultRuleEvaluator.class.getName();
  }

  public static class XML
  {

    public static class NODE
    {
      /** Configuration file XML Node */
      public static final String ROOLIE_RULES_CONFIG =
              "roolie-rules-config";
      /** Configuration file XML Node */
      public static final String RULE_DEFINITIONS = "rule-definitions";
      /** Configuration file XML Node */
      public static final String RULE_IMPLEMENTATIONS =
              "rule-implementations";
      /** Configuration file XML Node */
      public static final String ROOLIE_CONFIG = "roolie-config";
      /** Configuration file XML Node */
      public static final String RULE_DEF = "rule-def";
      /** Configuration file XML Node */
      public static final String RULE = "rule";
      /** Configuration file XML Node */
      public static final String RULE_IMPL = "rule-impl";
      /** Configuration file XML Node */
      public static final String PROPERTY = "property";
      /** Configuration file XML Node */
      public static final String LIST = "list";
      /** Configuration file XML Node */
      public static final String LIST_ITEM = "list-item";
      /** Configuration file XML Node */
      public static final String RULE_FACTORY_CLASS = "rule-factory-class";
      /** Configuration file XML Node */
      public static final String RULE_EVALUATOR_CLASS = "rule-evaluator-class";
    }

    public static class ATTRIB
    {
      /** Configuration file XML Attribute */
      public static final String NAME = "name";
      /** Configuration file XML Attribute */
      public static final String OR_NEXT_RULE = "or-next-rule";
      /** Configuration file XML Attribute */
      public static final String INVERSE = "inverse";
      /** Configuration file XML Attribute */
      public static final String CLASS = "class";
      /** Configuration file XML Attribute */
      public static final String VALUE = "value";
    }
  }
}
