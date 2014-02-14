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

package org.roolie.config.elmt;

import java.util.List;

public class RuleImplElmt
{
  protected String ruleName;
  protected String ruleClass;
  protected List<PropertyElmt> propertyElmts;

  public List<PropertyElmt> getPropertyElmts()
  {
    return propertyElmts;
  }

  public void setPropertyElmts(List<PropertyElmt> propertyElmts)
  {
    this.propertyElmts = propertyElmts;
  }

  public String getRuleClass()
  {
    return ruleClass;
  }

  public void setRuleClass(String ruleClass)
  {
    this.ruleClass = ruleClass;
  }

  public String getRuleName()
  {
    return ruleName;
  }

  public void setRuleName(String ruleName)
  {
    this.ruleName = ruleName;
  }
}
