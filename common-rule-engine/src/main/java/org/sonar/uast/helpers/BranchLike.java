/*
 * SonarQube Go Plugin
 * Copyright (C) 2018-2019 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.uast.helpers;

import javax.annotation.Nullable;
import org.sonar.uast.UastNode;

public class BranchLike {

  private final UastNode node;

  @Nullable
  private final UastNode label;

  private BranchLike(UastNode node, @Nullable UastNode label) {
    this.node = node;
    this.label = label;
  }

  public static BranchLike from(UastNode node) {
    if (node.is(UastNode.Kind.BREAK, UastNode.Kind.CONTINUE, UastNode.Kind.GOTO)) {
      return new BranchLike(node, node.getChild(UastNode.Kind.BRANCH_LABEL).orElse(null));
    }
    return null;
  }

  public UastNode node() {
    return node;
  }

  @Nullable
  public UastNode label() {
    return label;
  }

}
