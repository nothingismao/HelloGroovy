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

package org.roolie;

/**
 * <p>
 *   All rules used by Roolie must implement this interface.
 * </p>
 * <p>
 *   In general, a
 *   Rule will be responsible for evaluating a "bite-sized" aspect of an
 *   application
 *   (i.e. Is a number in a given range? Is a user logged in?).  These rules can
 *   be chained together with other rules to build more complex rules using the
 *   configuration file, although they may be used individually as well.
 * </p>
 * <p>
 *   Rule implementation class requirements:
 *   <ul>
 *     <li>Must have a no-arg constructor.</li>
 *     <li>
 *       If the Roolie mechanism of populating properties from the
 *       configuration file is used,  properties must be
 *       <code>java.lang.String</code> or
 *       <code>java.util.List&lt;String&gt;</code> (Of course you may parse them
 *       to whatever data type you wish when you use those values).
 *     </li>
 *     <li>
 *       Wrap checked exceptions in unchecked exceptions within the passes()
 *       method, or handle them there.
 *     </li>
 *   </ul>
 * </p>
 */
public interface Rule
{
  /**
   * Whether a rule passes.
   * @param ruleArgs The things this rule is to evaluate.
   * @return Whether the ruleArgs pass the evaluation.
   */
  public boolean passes(RuleArgs ruleArgs);
}
