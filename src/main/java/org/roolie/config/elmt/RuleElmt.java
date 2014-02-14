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

public class RuleElmt
{
  protected String name;
  protected boolean orNextRule = false;
  protected boolean inverse = false;
  protected RuleImplElmt ruleImplElmtRef;

  public boolean isInverse()
  {
    return inverse;
  }

  public void setInverse(boolean inverse)
  {
    this.inverse = inverse;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public boolean isOrNextRule()
  {
    return orNextRule;
  }

  public void setOrNextRule(boolean orNextRule)
  {
    this.orNextRule = orNextRule;
  }

  public RuleImplElmt getRuleImplElmtRef()
  {
    return ruleImplElmtRef;
  }

  public void setRuleImplElmtRef(RuleImplElmt ruleImplElmtRef)
  {
    this.ruleImplElmtRef = ruleImplElmtRef;
  }

}
