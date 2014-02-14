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
import org.roolie.RuleArgs;


public class BankingRuleArgs extends RuleArgs
{
  public enum ArgField
  {
   User
   , AccountBalance
   , WithdrawlAmountToday
   , RequestedWithdrawlAmount
   , RequestedDespositAmount
  };

  public String getUser()
  {
    return getString(ArgField.User);
  }

  public void setUser(String user)
  {
    setString(ArgField.User, user);
  }

  public boolean isUserSet()
  {
    return isArgSet(ArgField.User);
  }

  public double getAccountBalance()
  {
    return getDouble(ArgField.AccountBalance);
  }

  public void setAccountBalance(double accountBalance)
  {
    setDouble(ArgField.AccountBalance, accountBalance);
  }

  public boolean isAccountBalanceSet()
  {
    return isArgSet(ArgField.AccountBalance);
  }

  public double getWithdrawlAmountToday()
  {
    return getDouble(ArgField.WithdrawlAmountToday);
  }

  public void setWithdrawlAmountToday(double withdrawlAmountToday)
  {
    setDouble(ArgField.WithdrawlAmountToday, withdrawlAmountToday);
  }

  public boolean isWithdrawlAmountTodaySet()
  {
    return isArgSet(ArgField.WithdrawlAmountToday);
  }

  public double getRequestedWithdrawlAmount()
  {
    return getDouble(ArgField.RequestedWithdrawlAmount);
  }

  public void setRequestedWithdrawlAmount(double requestedWithdrawlAmount)
  {
    setDouble(ArgField.RequestedWithdrawlAmount, requestedWithdrawlAmount);
  }

  public boolean isRequestedWithdrawlAmountSet()
  {
    return isArgSet(ArgField.RequestedWithdrawlAmount);
  }

  public double getRequestedDepositAmount()
  {
    return getDouble(ArgField.RequestedDespositAmount);
  }

  public void setRequestedDepositAmount(double requestedDepositAmount)
  {
    setDouble(ArgField.RequestedDespositAmount, requestedDepositAmount);
  }

  public boolean isRequestedDepositAmountSet()
  {
    return isArgSet(ArgField.RequestedDespositAmount);
  }
}
