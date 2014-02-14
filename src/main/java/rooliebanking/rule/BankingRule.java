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

import org.roolie.Rule;
import org.roolie.RuleArgs;
import rooliebanking.Log;

/**
 * The abstract parent of all our banking rules
 */
public abstract class BankingRule implements Rule
{

  /**
   * We do some simple validation of the arguments here, then pass control
   * to the child class.
   */
  public boolean passes(RuleArgs ruleArgs)
  {
    // Validate the ruleArgs for all BankingRule instances

    // ruleArgs must be an instance of BankingRuleArgs
    if (false == (ruleArgs instanceof BankingRuleArgs))
    {
      Log.msg("ruleArgs must be an instance of BankingRuleArgs");
      return false;
    }

    // Cast RuleArgs to BankingRuleArgs and validate
    BankingRuleArgs bankArgs = (BankingRuleArgs) ruleArgs;

    // Muse have all args set
    if (false == bankArgs.isAccountBalanceSet()
      || false == bankArgs.isRequestedWithdrawlAmountSet() 
      || false == bankArgs.isUserSet() 
      || false == 
        (bankArgs.isWithdrawlAmountTodaySet()
          || bankArgs.isRequestedDepositAmountSet()))
    {
      Log.msg("Not all the arguments in BankingRuleArgs are set.");
      return false;
    }

    // If all args are there, let the child class do its evaluation
    return passes(bankArgs);
  }

  /**
   * An overloaded version of passes() that takes a BankingRuleArgs instance.
   */
  public abstract boolean passes(BankingRuleArgs ruleArgs);
}
