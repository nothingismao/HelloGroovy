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

public class PropertyElmt
{
  protected String name;
  protected String value;
  protected List<ListItemElmt> listItems;

  public boolean isListProperty()
  {
    boolean isListProperty = false;
    if (null != getListItems())
    {
      isListProperty = true;
    }
    return isListProperty;
  }

  public List<ListItemElmt> getListItems()
  {
    return listItems;
  }

  public void setListItems(List<ListItemElmt> listItems)
  {
    this.listItems = listItems;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }
}
