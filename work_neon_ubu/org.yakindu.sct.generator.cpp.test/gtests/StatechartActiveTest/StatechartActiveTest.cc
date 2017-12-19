/**
* Copyright (c) 2016 committers of YAKINDU and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     committers of YAKINDU - initial API and implementation
*/
#include <string>
#include "gtest/gtest.h"
#include "StatechartActive.h"

TEST(StatemachineTest, inactiveBeforeEnter) {
	StatechartActive* statechart = new StatechartActive();
	statechart->init();
	EXPECT_TRUE(!statechart->isActive());
	delete statechart;
}
TEST(StatemachineTest, activeAfterEnter) {
	StatechartActive* statechart = new StatechartActive();
	statechart->init();
	statechart->enter();
	EXPECT_TRUE(statechart->isActive());
	delete statechart;
}
TEST(StatemachineTest, inactiveAfterExit) {
	StatechartActive* statechart = new StatechartActive();
	statechart->init();
	statechart->enter();
	statechart->exit();
	EXPECT_TRUE(!statechart->isActive());
	delete statechart;
}
TEST(StatemachineTest, activeAfterReenter) {
	StatechartActive* statechart = new StatechartActive();
	statechart->init();
	statechart->enter();
	statechart->exit();
	statechart->enter();
	EXPECT_TRUE(statechart->isActive());
	delete statechart;
}
