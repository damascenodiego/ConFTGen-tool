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
#include "ConstOnlyInternalScope.h"

TEST(StatemachineTest, statechartEntry) {
	ConstOnlyInternalScope* statechart = new ConstOnlyInternalScope();
	statechart->init();
	statechart->enter();
	EXPECT_TRUE(statechart->isStateActive(ConstOnlyInternalScope::ConstOnlyInternalScope_main_region_A));
	delete statechart;
}
TEST(StatemachineTest, stateTransition) {
	ConstOnlyInternalScope* statechart = new ConstOnlyInternalScope();
	statechart->init();
	statechart->enter();
	statechart->raise_e( 1l);
	statechart->runCycle();
	EXPECT_TRUE(statechart->isStateActive(ConstOnlyInternalScope::ConstOnlyInternalScope_main_region_B));
	delete statechart;
}
