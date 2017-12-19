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
#include "ConstOnlyNamedScope.h"

ConstOnlyNamedScope handle;

TEST(StatemachineTest, statechartEntry) {
	constOnlyNamedScope_init(&handle);
	constOnlyNamedScope_enter(&handle);
	EXPECT_TRUE(constOnlyNamedScope_isStateActive(&handle, ConstOnlyNamedScope_ConstOnlyNamedScope_main_region_A));
}
TEST(StatemachineTest, stateTransition) {
	constOnlyNamedScope_init(&handle);
	constOnlyNamedScope_enter(&handle);
	constOnlyNamedScopeIface_raise_e(&handle, 1l);
	constOnlyNamedScope_runCycle(&handle);
	EXPECT_TRUE(constOnlyNamedScope_isStateActive(&handle, ConstOnlyNamedScope_ConstOnlyNamedScope_main_region_B));
}

