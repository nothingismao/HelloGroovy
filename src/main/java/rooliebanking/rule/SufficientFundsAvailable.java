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
import rooliebanking.Log;


public class SufficientFundsAvailable extends BankingRule
{

  @Override
  public boolean passes(BankingRuleArgs ruleArgs)
  {
    double availableFunds = ruleArgs.getAccountBalance();
    double requestedWithdrawl = ruleArgs.getRequestedWithdrawlAmount();
    double difference = availableFunds - requestedWithdrawl;
    boolean passes = (difference >= 0);
    if (passes)
    {
      Log.msg(ruleArgs.getUser() + " has sufficient funds.");
    }
    else
    {
      Log.msg(ruleArgs.getUser() + " does not have sufficient funds.");
    }
    return passes;
  }

}
