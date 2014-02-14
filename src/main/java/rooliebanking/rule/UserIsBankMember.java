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


package rooliebanking.rule;
import java.util.List;
import rooliebanking.Log;

/**
 * Evaluates whether the user is a member of our bank.
 */
public class UserIsBankMember extends BankingRule
{

  /**
   * All the configured members of the bank.
   */
  protected List<String> bankMembers;

  /**
   * Name of this bank
   */
  protected String bankName;

  @Override
  public boolean passes(BankingRuleArgs ruleArgs)
  {
    boolean passes = bankMembers.contains(ruleArgs.getUser());
    if (passes)
    {
      Log.msg(ruleArgs.getUser() + " is a member of " + bankName + ".");
    }
    else
    {
      Log.msg(ruleArgs.getUser() + " is not a member of " + bankName + ".");
    }
    return passes;
  }

}
