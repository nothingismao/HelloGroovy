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


public class ExceedsDailyWithdrawlLimit extends BankingRule
{
  protected String dailyWithdrawlLimit;

  @Override
  public boolean passes(BankingRuleArgs ruleArgs)
  {
    double dDailyWithdrawlLimit = Double.parseDouble(dailyWithdrawlLimit);
    double requestedWithdrawlAmount = ruleArgs.getRequestedWithdrawlAmount();
    double withdrawlAmountToday = ruleArgs.getWithdrawlAmountToday();
    boolean passes =
      (requestedWithdrawlAmount + withdrawlAmountToday <= dDailyWithdrawlLimit);
    if (passes)
    {
      Log.msg("Does not exceed " 
        + ruleArgs.getUser() 
        + "'s daily withdrawl limit.");
    }
    else
    {
      Log.msg("Exceeds " 
        + ruleArgs.getUser() 
        + "'s daily withdrawl limit.");
    }
    return passes;
  }

}
