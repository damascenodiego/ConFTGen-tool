
#include <stdlib.h>
#include <string.h>
#include "sc_types.h"
#include "Trafficlight.h"
#include "TrafficlightRequired.h"
/*! \file Implementation of the state machine 'Trafficlight'
*/

/* prototypes of all internal functions */
static sc_boolean trafficlight_check_main_region_on_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_on_r1_StreetGreen_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_on_r1_StreetRedYellow_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_on_r1_StreetRed_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_on_r1_PedestrianGreen_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_on_r1_PedestrianRed_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_on_r1_StreetAttention_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_on_r1_PedestrianRequesting_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_on_r1_PedestrianRequesting_r2_waitOn_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_on_r1_PedestrianRequesting_r2_waitOff_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_off_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_off_r1_YellowOn_tr0_tr0(const Trafficlight* handle);
static sc_boolean trafficlight_check_main_region_off_r1_YellowOff_tr0_tr0(const Trafficlight* handle);
static void trafficlight_effect_main_region_on_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_on_r1_StreetGreen_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_on_r1_StreetRedYellow_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_on_r1_StreetRed_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_on_r1_PedestrianGreen_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_on_r1_PedestrianRed_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_on_r1_StreetAttention_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_on_r1_PedestrianRequesting_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_on_r1_PedestrianRequesting_r2_waitOn_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_on_r1_PedestrianRequesting_r2_waitOff_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_off_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_off_r1_YellowOn_tr0(Trafficlight* handle);
static void trafficlight_effect_main_region_off_r1_YellowOff_tr0(Trafficlight* handle);
static void trafficlight_enact_main_region_on_r1_StreetGreen(Trafficlight* handle);
static void trafficlight_enact_main_region_on_r1_StreetRedYellow(Trafficlight* handle);
static void trafficlight_enact_main_region_on_r1_StreetRed(Trafficlight* handle);
static void trafficlight_enact_main_region_on_r1_PedestrianGreen(Trafficlight* handle);
static void trafficlight_enact_main_region_on_r1_PedestrianRed(Trafficlight* handle);
static void trafficlight_enact_main_region_on_r1_StreetAttention(Trafficlight* handle);
static void trafficlight_enact_main_region_on_r1_PedestrianRequesting(Trafficlight* handle);
static void trafficlight_enact_main_region_on_r1_PedestrianRequesting_r2_waitOn(Trafficlight* handle);
static void trafficlight_enact_main_region_on_r1_PedestrianRequesting_r2_waitOff(Trafficlight* handle);
static void trafficlight_enact_main_region_off(Trafficlight* handle);
static void trafficlight_enact_main_region_off_r1_YellowOn(Trafficlight* handle);
static void trafficlight_enact_main_region_off_r1_YellowOff(Trafficlight* handle);
static void trafficlight_exact_main_region_on_r1_StreetRedYellow(Trafficlight* handle);
static void trafficlight_exact_main_region_on_r1_StreetRed(Trafficlight* handle);
static void trafficlight_exact_main_region_on_r1_PedestrianGreen(Trafficlight* handle);
static void trafficlight_exact_main_region_on_r1_PedestrianRed(Trafficlight* handle);
static void trafficlight_exact_main_region_on_r1_StreetAttention(Trafficlight* handle);
static void trafficlight_exact_main_region_on_r1_PedestrianRequesting(Trafficlight* handle);
static void trafficlight_exact_main_region_on_r1_PedestrianRequesting_r2_waitOn(Trafficlight* handle);
static void trafficlight_exact_main_region_on_r1_PedestrianRequesting_r2_waitOff(Trafficlight* handle);
static void trafficlight_exact_main_region_off_r1_YellowOn(Trafficlight* handle);
static void trafficlight_exact_main_region_off_r1_YellowOff(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_StreetGreen_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_StreetRedYellow_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_StreetRed_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_PedestrianGreen_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_PedestrianRed_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_StreetAttention_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_PedestrianRequesting_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_PedestrianRequesting_r2_waitOn_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_PedestrianRequesting_r2_waitOff_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_off_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_off_r1_YellowOn_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_off_r1_YellowOff_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_on_r1_PedestrianRequesting_r2_default(Trafficlight* handle);
static void trafficlight_enseq_main_region_off_r1_default(Trafficlight* handle);
static void trafficlight_exseq_main_region_on(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1_StreetGreen(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1_StreetRedYellow(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1_StreetRed(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1_PedestrianGreen(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1_PedestrianRed(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1_StreetAttention(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1_PedestrianRequesting(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOn(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOff(Trafficlight* handle);
static void trafficlight_exseq_main_region_off(Trafficlight* handle);
static void trafficlight_exseq_main_region_off_r1_YellowOn(Trafficlight* handle);
static void trafficlight_exseq_main_region_off_r1_YellowOff(Trafficlight* handle);
static void trafficlight_exseq_main_region(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1(Trafficlight* handle);
static void trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2(Trafficlight* handle);
static void trafficlight_exseq_main_region_off_r1(Trafficlight* handle);
static void trafficlight_react_main_region_on_r1_StreetGreen(Trafficlight* handle);
static void trafficlight_react_main_region_on_r1_StreetRedYellow(Trafficlight* handle);
static void trafficlight_react_main_region_on_r1_StreetRed(Trafficlight* handle);
static void trafficlight_react_main_region_on_r1_PedestrianGreen(Trafficlight* handle);
static void trafficlight_react_main_region_on_r1_PedestrianRed(Trafficlight* handle);
static void trafficlight_react_main_region_on_r1_StreetAttention(Trafficlight* handle);
static void trafficlight_react_main_region_on_r1_PedestrianRequesting_r2_waitOn(Trafficlight* handle);
static void trafficlight_react_main_region_on_r1_PedestrianRequesting_r2_waitOff(Trafficlight* handle);
static void trafficlight_react_main_region_off_r1_YellowOn(Trafficlight* handle);
static void trafficlight_react_main_region_off_r1_YellowOff(Trafficlight* handle);
static void trafficlight_react_main_region_on_r1__entry_Default(Trafficlight* handle);
static void trafficlight_react_main_region_on_r1_PedestrianRequesting_r2__entry_Default(Trafficlight* handle);
static void trafficlight_react_main_region__entry_Default(Trafficlight* handle);
static void trafficlight_react_main_region_off_r1__entry_Default(Trafficlight* handle);
static void trafficlight_clearInEvents(Trafficlight* handle);
static void trafficlight_clearOutEvents(Trafficlight* handle);


void trafficlight_init(Trafficlight* handle)
{
	sc_integer i;

	for (i = 0; i < TRAFFICLIGHT_MAX_ORTHOGONAL_STATES; ++i)
	{
		handle->stateConfVector[i] = Trafficlight_last_state;
	}
	
	
	handle->stateConfVectorPosition = 0;

	trafficlight_clearInEvents(handle);
	trafficlight_clearOutEvents(handle);

	/* Default init sequence for statechart Trafficlight */
	handle->ifaceTrafficLight.red = bool_false;
	handle->ifaceTrafficLight.yellow = bool_false;
	handle->ifaceTrafficLight.green = bool_false;
	handle->ifacePedestrianLight.red = bool_false;
	handle->ifacePedestrianLight.green = bool_false;
	handle->ifacePedestrianLight.wait = bool_false;

}

void trafficlight_enter(Trafficlight* handle)
{
	/* Default enter sequence for statechart Trafficlight */
	trafficlight_enseq_main_region_default(handle);
}

void trafficlight_exit(Trafficlight* handle)
{
	/* Default exit sequence for statechart Trafficlight */
	trafficlight_exseq_main_region(handle);
}

sc_boolean trafficlight_isActive(const Trafficlight* handle)
{
	sc_boolean result;
	if (handle->stateConfVector[0] != Trafficlight_last_state)
	{
		result =  bool_true;
	}
	else
	{
		result = bool_false;
	}
	return result;
}

/* 
 * Always returns 'false' since this state machine can never become final.
 */
sc_boolean trafficlight_isFinal(const Trafficlight* handle)
{
   return bool_false;
}

static void trafficlight_clearInEvents(Trafficlight* handle)
{
	handle->iface.pedestrianRequest_raised = bool_false;
	handle->iface.onOff_raised = bool_false;
	handle->timeEvents.trafficlight_main_region_on_r1_StreetRedYellow_tev0_raised = bool_false;
	handle->timeEvents.trafficlight_main_region_on_r1_StreetRed_tev0_raised = bool_false;
	handle->timeEvents.trafficlight_main_region_on_r1_PedestrianGreen_tev0_raised = bool_false;
	handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRed_tev0_raised = bool_false;
	handle->timeEvents.trafficlight_main_region_on_r1_StreetAttention_tev0_raised = bool_false;
	handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_tev0_raised = bool_false;
	handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn_tev0_raised = bool_false;
	handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff_tev0_raised = bool_false;
	handle->timeEvents.trafficlight_main_region_off_r1_YellowOn_tev0_raised = bool_false;
	handle->timeEvents.trafficlight_main_region_off_r1_YellowOff_tev0_raised = bool_false;
}

static void trafficlight_clearOutEvents(Trafficlight* handle)
{
}

void trafficlight_runCycle(Trafficlight* handle)
{
	
	trafficlight_clearOutEvents(handle);
	
	for (handle->stateConfVectorPosition = 0;
		handle->stateConfVectorPosition < TRAFFICLIGHT_MAX_ORTHOGONAL_STATES;
		handle->stateConfVectorPosition++)
		{
			
		switch (handle->stateConfVector[handle->stateConfVectorPosition])
		{
		case Trafficlight_main_region_on_r1_StreetGreen :
		{
			trafficlight_react_main_region_on_r1_StreetGreen(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_StreetRedYellow :
		{
			trafficlight_react_main_region_on_r1_StreetRedYellow(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_StreetRed :
		{
			trafficlight_react_main_region_on_r1_StreetRed(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianGreen :
		{
			trafficlight_react_main_region_on_r1_PedestrianGreen(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianRed :
		{
			trafficlight_react_main_region_on_r1_PedestrianRed(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_StreetAttention :
		{
			trafficlight_react_main_region_on_r1_StreetAttention(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn :
		{
			trafficlight_react_main_region_on_r1_PedestrianRequesting_r2_waitOn(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff :
		{
			trafficlight_react_main_region_on_r1_PedestrianRequesting_r2_waitOff(handle);
			break;
		}
		case Trafficlight_main_region_off_r1_YellowOn :
		{
			trafficlight_react_main_region_off_r1_YellowOn(handle);
			break;
		}
		case Trafficlight_main_region_off_r1_YellowOff :
		{
			trafficlight_react_main_region_off_r1_YellowOff(handle);
			break;
		}
		default:
			break;
		}
	}
	
	trafficlight_clearInEvents(handle);
}

void trafficlight_raiseTimeEvent(const Trafficlight* handle, sc_eventid evid)
{
	if ( ((sc_intptr_t)evid) >= ((sc_intptr_t)&(handle->timeEvents))
		&&  ((sc_intptr_t)evid) < ((sc_intptr_t)&(handle->timeEvents)) + sizeof(TrafficlightTimeEvents))
		{
		*(sc_boolean*)evid = bool_true;
	}		
}

sc_boolean trafficlight_isStateActive(const Trafficlight* handle, TrafficlightStates state)
{
	sc_boolean result = bool_false;
	switch (state)
	{
		case Trafficlight_main_region_on :
			result = (sc_boolean) (handle->stateConfVector[0] >= Trafficlight_main_region_on
				&& handle->stateConfVector[0] <= Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff);
			break;
		case Trafficlight_main_region_on_r1_StreetGreen :
			result = (sc_boolean) (handle->stateConfVector[0] == Trafficlight_main_region_on_r1_StreetGreen
			);
			break;
		case Trafficlight_main_region_on_r1_StreetRedYellow :
			result = (sc_boolean) (handle->stateConfVector[0] == Trafficlight_main_region_on_r1_StreetRedYellow
			);
			break;
		case Trafficlight_main_region_on_r1_StreetRed :
			result = (sc_boolean) (handle->stateConfVector[0] == Trafficlight_main_region_on_r1_StreetRed
			);
			break;
		case Trafficlight_main_region_on_r1_PedestrianGreen :
			result = (sc_boolean) (handle->stateConfVector[0] == Trafficlight_main_region_on_r1_PedestrianGreen
			);
			break;
		case Trafficlight_main_region_on_r1_PedestrianRed :
			result = (sc_boolean) (handle->stateConfVector[0] == Trafficlight_main_region_on_r1_PedestrianRed
			);
			break;
		case Trafficlight_main_region_on_r1_StreetAttention :
			result = (sc_boolean) (handle->stateConfVector[0] == Trafficlight_main_region_on_r1_StreetAttention
			);
			break;
		case Trafficlight_main_region_on_r1_PedestrianRequesting :
			result = (sc_boolean) (handle->stateConfVector[0] >= Trafficlight_main_region_on_r1_PedestrianRequesting
				&& handle->stateConfVector[0] <= Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff);
			break;
		case Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn :
			result = (sc_boolean) (handle->stateConfVector[0] == Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn
			);
			break;
		case Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff :
			result = (sc_boolean) (handle->stateConfVector[0] == Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff
			);
			break;
		case Trafficlight_main_region_off :
			result = (sc_boolean) (handle->stateConfVector[0] >= Trafficlight_main_region_off
				&& handle->stateConfVector[0] <= Trafficlight_main_region_off_r1_YellowOff);
			break;
		case Trafficlight_main_region_off_r1_YellowOn :
			result = (sc_boolean) (handle->stateConfVector[0] == Trafficlight_main_region_off_r1_YellowOn
			);
			break;
		case Trafficlight_main_region_off_r1_YellowOff :
			result = (sc_boolean) (handle->stateConfVector[0] == Trafficlight_main_region_off_r1_YellowOff
			);
			break;
		default:
			result = bool_false;
			break;
	}
	return result;
}



sc_boolean trafficlightIfaceTrafficLight_get_red(const Trafficlight* handle)
{
	return handle->ifaceTrafficLight.red;
}
void trafficlightIfaceTrafficLight_set_red(Trafficlight* handle, sc_boolean value)
{
	handle->ifaceTrafficLight.red = value;
}
sc_boolean trafficlightIfaceTrafficLight_get_yellow(const Trafficlight* handle)
{
	return handle->ifaceTrafficLight.yellow;
}
void trafficlightIfaceTrafficLight_set_yellow(Trafficlight* handle, sc_boolean value)
{
	handle->ifaceTrafficLight.yellow = value;
}
sc_boolean trafficlightIfaceTrafficLight_get_green(const Trafficlight* handle)
{
	return handle->ifaceTrafficLight.green;
}
void trafficlightIfaceTrafficLight_set_green(Trafficlight* handle, sc_boolean value)
{
	handle->ifaceTrafficLight.green = value;
}


sc_boolean trafficlightIfacePedestrianLight_get_red(const Trafficlight* handle)
{
	return handle->ifacePedestrianLight.red;
}
void trafficlightIfacePedestrianLight_set_red(Trafficlight* handle, sc_boolean value)
{
	handle->ifacePedestrianLight.red = value;
}
sc_boolean trafficlightIfacePedestrianLight_get_green(const Trafficlight* handle)
{
	return handle->ifacePedestrianLight.green;
}
void trafficlightIfacePedestrianLight_set_green(Trafficlight* handle, sc_boolean value)
{
	handle->ifacePedestrianLight.green = value;
}
sc_boolean trafficlightIfacePedestrianLight_get_wait(const Trafficlight* handle)
{
	return handle->ifacePedestrianLight.wait;
}
void trafficlightIfacePedestrianLight_set_wait(Trafficlight* handle, sc_boolean value)
{
	handle->ifacePedestrianLight.wait = value;
}
void trafficlightIface_raise_pedestrianRequest(Trafficlight* handle)
{
	handle->iface.pedestrianRequest_raised = bool_true;
}
void trafficlightIface_raise_onOff(Trafficlight* handle)
{
	handle->iface.onOff_raised = bool_true;
}



/* implementations of all internal functions */

static sc_boolean trafficlight_check_main_region_on_tr0_tr0(const Trafficlight* handle)
{
	return handle->iface.onOff_raised;
}

static sc_boolean trafficlight_check_main_region_on_r1_StreetGreen_tr0_tr0(const Trafficlight* handle)
{
	return handle->iface.pedestrianRequest_raised;
}

static sc_boolean trafficlight_check_main_region_on_r1_StreetRedYellow_tr0_tr0(const Trafficlight* handle)
{
	return handle->timeEvents.trafficlight_main_region_on_r1_StreetRedYellow_tev0_raised;
}

static sc_boolean trafficlight_check_main_region_on_r1_StreetRed_tr0_tr0(const Trafficlight* handle)
{
	return handle->timeEvents.trafficlight_main_region_on_r1_StreetRed_tev0_raised;
}

static sc_boolean trafficlight_check_main_region_on_r1_PedestrianGreen_tr0_tr0(const Trafficlight* handle)
{
	return handle->timeEvents.trafficlight_main_region_on_r1_PedestrianGreen_tev0_raised;
}

static sc_boolean trafficlight_check_main_region_on_r1_PedestrianRed_tr0_tr0(const Trafficlight* handle)
{
	return handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRed_tev0_raised;
}

static sc_boolean trafficlight_check_main_region_on_r1_StreetAttention_tr0_tr0(const Trafficlight* handle)
{
	return handle->timeEvents.trafficlight_main_region_on_r1_StreetAttention_tev0_raised;
}

static sc_boolean trafficlight_check_main_region_on_r1_PedestrianRequesting_tr0_tr0(const Trafficlight* handle)
{
	return handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_tev0_raised;
}

static sc_boolean trafficlight_check_main_region_on_r1_PedestrianRequesting_r2_waitOn_tr0_tr0(const Trafficlight* handle)
{
	return handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn_tev0_raised;
}

static sc_boolean trafficlight_check_main_region_on_r1_PedestrianRequesting_r2_waitOff_tr0_tr0(const Trafficlight* handle)
{
	return handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff_tev0_raised;
}

static sc_boolean trafficlight_check_main_region_off_tr0_tr0(const Trafficlight* handle)
{
	return handle->iface.onOff_raised;
}

static sc_boolean trafficlight_check_main_region_off_r1_YellowOn_tr0_tr0(const Trafficlight* handle)
{
	return handle->timeEvents.trafficlight_main_region_off_r1_YellowOn_tev0_raised;
}

static sc_boolean trafficlight_check_main_region_off_r1_YellowOff_tr0_tr0(const Trafficlight* handle)
{
	return handle->timeEvents.trafficlight_main_region_off_r1_YellowOff_tev0_raised;
}

static void trafficlight_effect_main_region_on_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_on(handle);
	trafficlight_enseq_main_region_off_default(handle);
}

static void trafficlight_effect_main_region_on_r1_StreetGreen_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_on_r1_StreetGreen(handle);
	trafficlight_enseq_main_region_on_r1_PedestrianRequesting_default(handle);
}

static void trafficlight_effect_main_region_on_r1_StreetRedYellow_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_on_r1_StreetRedYellow(handle);
	trafficlight_enseq_main_region_on_r1_StreetGreen_default(handle);
}

static void trafficlight_effect_main_region_on_r1_StreetRed_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_on_r1_StreetRed(handle);
	trafficlight_enseq_main_region_on_r1_PedestrianGreen_default(handle);
}

static void trafficlight_effect_main_region_on_r1_PedestrianGreen_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_on_r1_PedestrianGreen(handle);
	trafficlight_enseq_main_region_on_r1_PedestrianRed_default(handle);
}

static void trafficlight_effect_main_region_on_r1_PedestrianRed_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_on_r1_PedestrianRed(handle);
	trafficlight_enseq_main_region_on_r1_StreetRedYellow_default(handle);
}

static void trafficlight_effect_main_region_on_r1_StreetAttention_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_on_r1_StreetAttention(handle);
	trafficlight_enseq_main_region_on_r1_StreetRed_default(handle);
}

static void trafficlight_effect_main_region_on_r1_PedestrianRequesting_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_on_r1_PedestrianRequesting(handle);
	trafficlight_enseq_main_region_on_r1_StreetAttention_default(handle);
}

static void trafficlight_effect_main_region_on_r1_PedestrianRequesting_r2_waitOn_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOn(handle);
	trafficlight_enseq_main_region_on_r1_PedestrianRequesting_r2_waitOff_default(handle);
}

static void trafficlight_effect_main_region_on_r1_PedestrianRequesting_r2_waitOff_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOff(handle);
	trafficlight_enseq_main_region_on_r1_PedestrianRequesting_r2_waitOn_default(handle);
}

static void trafficlight_effect_main_region_off_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_off(handle);
	trafficlight_enseq_main_region_on_default(handle);
}

static void trafficlight_effect_main_region_off_r1_YellowOn_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_off_r1_YellowOn(handle);
	trafficlight_enseq_main_region_off_r1_YellowOff_default(handle);
}

static void trafficlight_effect_main_region_off_r1_YellowOff_tr0(Trafficlight* handle)
{
	trafficlight_exseq_main_region_off_r1_YellowOff(handle);
	trafficlight_enseq_main_region_off_r1_YellowOn_default(handle);
}

/* Entry action for state 'StreetGreen'. */
static void trafficlight_enact_main_region_on_r1_StreetGreen(Trafficlight* handle)
{
	/* Entry action for state 'StreetGreen'. */
	handle->ifaceTrafficLight.red = bool_false;
	handle->ifaceTrafficLight.yellow = bool_false;
	handle->ifaceTrafficLight.green = bool_true;
	handle->ifacePedestrianLight.wait = bool_false;
}

/* Entry action for state 'StreetRedYellow'. */
static void trafficlight_enact_main_region_on_r1_StreetRedYellow(Trafficlight* handle)
{
	/* Entry action for state 'StreetRedYellow'. */
	trafficlight_setTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_StreetRedYellow_tev0_raised) , 2 * 1000, bool_false);
	handle->ifaceTrafficLight.red = bool_true;
	handle->ifaceTrafficLight.yellow = bool_true;
	handle->ifaceTrafficLight.green = bool_false;
	handle->ifacePedestrianLight.wait = bool_false;
}

/* Entry action for state 'StreetRed'. */
static void trafficlight_enact_main_region_on_r1_StreetRed(Trafficlight* handle)
{
	/* Entry action for state 'StreetRed'. */
	trafficlight_setTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_StreetRed_tev0_raised) , 2 * 1000, bool_false);
	handle->ifaceTrafficLight.red = bool_true;
	handle->ifaceTrafficLight.yellow = bool_false;
	handle->ifaceTrafficLight.green = bool_false;
	handle->ifacePedestrianLight.wait = bool_false;
}

/* Entry action for state 'PedestrianGreen'. */
static void trafficlight_enact_main_region_on_r1_PedestrianGreen(Trafficlight* handle)
{
	/* Entry action for state 'PedestrianGreen'. */
	trafficlight_setTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_PedestrianGreen_tev0_raised) , 7 * 1000, bool_false);
	handle->ifacePedestrianLight.red = bool_false;
	handle->ifacePedestrianLight.green = bool_true;
	handle->ifacePedestrianLight.wait = bool_false;
}

/* Entry action for state 'PedestrianRed'. */
static void trafficlight_enact_main_region_on_r1_PedestrianRed(Trafficlight* handle)
{
	/* Entry action for state 'PedestrianRed'. */
	trafficlight_setTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRed_tev0_raised) , 500, bool_false);
	handle->ifacePedestrianLight.red = bool_true;
	handle->ifacePedestrianLight.green = bool_false;
	handle->ifacePedestrianLight.wait = bool_false;
}

/* Entry action for state 'StreetAttention'. */
static void trafficlight_enact_main_region_on_r1_StreetAttention(Trafficlight* handle)
{
	/* Entry action for state 'StreetAttention'. */
	trafficlight_setTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_StreetAttention_tev0_raised) , 2 * 1000, bool_false);
	handle->ifaceTrafficLight.red = bool_false;
	handle->ifaceTrafficLight.yellow = bool_true;
	handle->ifaceTrafficLight.green = bool_false;
	handle->ifacePedestrianLight.wait = bool_false;
}

/* Entry action for state 'PedestrianRequesting'. */
static void trafficlight_enact_main_region_on_r1_PedestrianRequesting(Trafficlight* handle)
{
	/* Entry action for state 'PedestrianRequesting'. */
	trafficlight_setTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_tev0_raised) , 5 * 1000, bool_false);
}

/* Entry action for state 'waitOn'. */
static void trafficlight_enact_main_region_on_r1_PedestrianRequesting_r2_waitOn(Trafficlight* handle)
{
	/* Entry action for state 'waitOn'. */
	trafficlight_setTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn_tev0_raised) , 500, bool_false);
	handle->ifacePedestrianLight.wait = bool_true;
}

/* Entry action for state 'waitOff'. */
static void trafficlight_enact_main_region_on_r1_PedestrianRequesting_r2_waitOff(Trafficlight* handle)
{
	/* Entry action for state 'waitOff'. */
	trafficlight_setTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff_tev0_raised) , 500, bool_false);
	handle->ifacePedestrianLight.wait = bool_false;
}

/* Entry action for state 'off'. */
static void trafficlight_enact_main_region_off(Trafficlight* handle)
{
	/* Entry action for state 'off'. */
	handle->ifaceTrafficLight.red = bool_false;
	handle->ifaceTrafficLight.yellow = bool_false;
	handle->ifaceTrafficLight.green = bool_false;
	handle->ifacePedestrianLight.red = bool_false;
	handle->ifacePedestrianLight.green = bool_false;
	handle->ifacePedestrianLight.wait = bool_false;
}

/* Entry action for state 'YellowOn'. */
static void trafficlight_enact_main_region_off_r1_YellowOn(Trafficlight* handle)
{
	/* Entry action for state 'YellowOn'. */
	trafficlight_setTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_off_r1_YellowOn_tev0_raised) , 500, bool_false);
	handle->ifaceTrafficLight.yellow = bool_true;
	handle->ifacePedestrianLight.wait = bool_true;
}

/* Entry action for state 'YellowOff'. */
static void trafficlight_enact_main_region_off_r1_YellowOff(Trafficlight* handle)
{
	/* Entry action for state 'YellowOff'. */
	trafficlight_setTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_off_r1_YellowOff_tev0_raised) , 500, bool_false);
	handle->ifaceTrafficLight.yellow = bool_false;
	handle->ifacePedestrianLight.wait = bool_false;
}

/* Exit action for state 'StreetRedYellow'. */
static void trafficlight_exact_main_region_on_r1_StreetRedYellow(Trafficlight* handle)
{
	/* Exit action for state 'StreetRedYellow'. */
	trafficlight_unsetTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_StreetRedYellow_tev0_raised) );		
}

/* Exit action for state 'StreetRed'. */
static void trafficlight_exact_main_region_on_r1_StreetRed(Trafficlight* handle)
{
	/* Exit action for state 'StreetRed'. */
	trafficlight_unsetTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_StreetRed_tev0_raised) );		
}

/* Exit action for state 'PedestrianGreen'. */
static void trafficlight_exact_main_region_on_r1_PedestrianGreen(Trafficlight* handle)
{
	/* Exit action for state 'PedestrianGreen'. */
	trafficlight_unsetTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_PedestrianGreen_tev0_raised) );		
}

/* Exit action for state 'PedestrianRed'. */
static void trafficlight_exact_main_region_on_r1_PedestrianRed(Trafficlight* handle)
{
	/* Exit action for state 'PedestrianRed'. */
	trafficlight_unsetTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRed_tev0_raised) );		
}

/* Exit action for state 'StreetAttention'. */
static void trafficlight_exact_main_region_on_r1_StreetAttention(Trafficlight* handle)
{
	/* Exit action for state 'StreetAttention'. */
	trafficlight_unsetTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_StreetAttention_tev0_raised) );		
}

/* Exit action for state 'PedestrianRequesting'. */
static void trafficlight_exact_main_region_on_r1_PedestrianRequesting(Trafficlight* handle)
{
	/* Exit action for state 'PedestrianRequesting'. */
	trafficlight_unsetTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_tev0_raised) );		
	handle->ifacePedestrianLight.wait = bool_false;
}

/* Exit action for state 'waitOn'. */
static void trafficlight_exact_main_region_on_r1_PedestrianRequesting_r2_waitOn(Trafficlight* handle)
{
	/* Exit action for state 'waitOn'. */
	trafficlight_unsetTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn_tev0_raised) );		
}

/* Exit action for state 'waitOff'. */
static void trafficlight_exact_main_region_on_r1_PedestrianRequesting_r2_waitOff(Trafficlight* handle)
{
	/* Exit action for state 'waitOff'. */
	trafficlight_unsetTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff_tev0_raised) );		
}

/* Exit action for state 'YellowOn'. */
static void trafficlight_exact_main_region_off_r1_YellowOn(Trafficlight* handle)
{
	/* Exit action for state 'YellowOn'. */
	trafficlight_unsetTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_off_r1_YellowOn_tev0_raised) );		
}

/* Exit action for state 'YellowOff'. */
static void trafficlight_exact_main_region_off_r1_YellowOff(Trafficlight* handle)
{
	/* Exit action for state 'YellowOff'. */
	trafficlight_unsetTimer(handle, (sc_eventid) &(handle->timeEvents.trafficlight_main_region_off_r1_YellowOff_tev0_raised) );		
}

/* 'default' enter sequence for state on */
static void trafficlight_enseq_main_region_on_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state on */
	trafficlight_enseq_main_region_on_r1_default(handle);
}

/* 'default' enter sequence for state StreetGreen */
static void trafficlight_enseq_main_region_on_r1_StreetGreen_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state StreetGreen */
	trafficlight_enact_main_region_on_r1_StreetGreen(handle);
	handle->stateConfVector[0] = Trafficlight_main_region_on_r1_StreetGreen;
	handle->stateConfVectorPosition = 0;
}

/* 'default' enter sequence for state StreetRedYellow */
static void trafficlight_enseq_main_region_on_r1_StreetRedYellow_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state StreetRedYellow */
	trafficlight_enact_main_region_on_r1_StreetRedYellow(handle);
	handle->stateConfVector[0] = Trafficlight_main_region_on_r1_StreetRedYellow;
	handle->stateConfVectorPosition = 0;
}

/* 'default' enter sequence for state StreetRed */
static void trafficlight_enseq_main_region_on_r1_StreetRed_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state StreetRed */
	trafficlight_enact_main_region_on_r1_StreetRed(handle);
	handle->stateConfVector[0] = Trafficlight_main_region_on_r1_StreetRed;
	handle->stateConfVectorPosition = 0;
}

/* 'default' enter sequence for state PedestrianGreen */
static void trafficlight_enseq_main_region_on_r1_PedestrianGreen_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state PedestrianGreen */
	trafficlight_enact_main_region_on_r1_PedestrianGreen(handle);
	handle->stateConfVector[0] = Trafficlight_main_region_on_r1_PedestrianGreen;
	handle->stateConfVectorPosition = 0;
}

/* 'default' enter sequence for state PedestrianRed */
static void trafficlight_enseq_main_region_on_r1_PedestrianRed_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state PedestrianRed */
	trafficlight_enact_main_region_on_r1_PedestrianRed(handle);
	handle->stateConfVector[0] = Trafficlight_main_region_on_r1_PedestrianRed;
	handle->stateConfVectorPosition = 0;
}

/* 'default' enter sequence for state StreetAttention */
static void trafficlight_enseq_main_region_on_r1_StreetAttention_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state StreetAttention */
	trafficlight_enact_main_region_on_r1_StreetAttention(handle);
	handle->stateConfVector[0] = Trafficlight_main_region_on_r1_StreetAttention;
	handle->stateConfVectorPosition = 0;
}

/* 'default' enter sequence for state PedestrianRequesting */
static void trafficlight_enseq_main_region_on_r1_PedestrianRequesting_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state PedestrianRequesting */
	trafficlight_enact_main_region_on_r1_PedestrianRequesting(handle);
	trafficlight_enseq_main_region_on_r1_PedestrianRequesting_r2_default(handle);
}

/* 'default' enter sequence for state waitOn */
static void trafficlight_enseq_main_region_on_r1_PedestrianRequesting_r2_waitOn_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state waitOn */
	trafficlight_enact_main_region_on_r1_PedestrianRequesting_r2_waitOn(handle);
	handle->stateConfVector[0] = Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn;
	handle->stateConfVectorPosition = 0;
}

/* 'default' enter sequence for state waitOff */
static void trafficlight_enseq_main_region_on_r1_PedestrianRequesting_r2_waitOff_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state waitOff */
	trafficlight_enact_main_region_on_r1_PedestrianRequesting_r2_waitOff(handle);
	handle->stateConfVector[0] = Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff;
	handle->stateConfVectorPosition = 0;
}

/* 'default' enter sequence for state off */
static void trafficlight_enseq_main_region_off_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state off */
	trafficlight_enact_main_region_off(handle);
	trafficlight_enseq_main_region_off_r1_default(handle);
}

/* 'default' enter sequence for state YellowOn */
static void trafficlight_enseq_main_region_off_r1_YellowOn_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state YellowOn */
	trafficlight_enact_main_region_off_r1_YellowOn(handle);
	handle->stateConfVector[0] = Trafficlight_main_region_off_r1_YellowOn;
	handle->stateConfVectorPosition = 0;
}

/* 'default' enter sequence for state YellowOff */
static void trafficlight_enseq_main_region_off_r1_YellowOff_default(Trafficlight* handle)
{
	/* 'default' enter sequence for state YellowOff */
	trafficlight_enact_main_region_off_r1_YellowOff(handle);
	handle->stateConfVector[0] = Trafficlight_main_region_off_r1_YellowOff;
	handle->stateConfVectorPosition = 0;
}

/* 'default' enter sequence for region main region */
static void trafficlight_enseq_main_region_default(Trafficlight* handle)
{
	/* 'default' enter sequence for region main region */
	trafficlight_react_main_region__entry_Default(handle);
}

/* 'default' enter sequence for region r1 */
static void trafficlight_enseq_main_region_on_r1_default(Trafficlight* handle)
{
	/* 'default' enter sequence for region r1 */
	trafficlight_react_main_region_on_r1__entry_Default(handle);
}

/* 'default' enter sequence for region r2 */
static void trafficlight_enseq_main_region_on_r1_PedestrianRequesting_r2_default(Trafficlight* handle)
{
	/* 'default' enter sequence for region r2 */
	trafficlight_react_main_region_on_r1_PedestrianRequesting_r2__entry_Default(handle);
}

/* 'default' enter sequence for region r1 */
static void trafficlight_enseq_main_region_off_r1_default(Trafficlight* handle)
{
	/* 'default' enter sequence for region r1 */
	trafficlight_react_main_region_off_r1__entry_Default(handle);
}

/* Default exit sequence for state on */
static void trafficlight_exseq_main_region_on(Trafficlight* handle)
{
	/* Default exit sequence for state on */
	trafficlight_exseq_main_region_on_r1(handle);
}

/* Default exit sequence for state StreetGreen */
static void trafficlight_exseq_main_region_on_r1_StreetGreen(Trafficlight* handle)
{
	/* Default exit sequence for state StreetGreen */
	handle->stateConfVector[0] = Trafficlight_last_state;
	handle->stateConfVectorPosition = 0;
}

/* Default exit sequence for state StreetRedYellow */
static void trafficlight_exseq_main_region_on_r1_StreetRedYellow(Trafficlight* handle)
{
	/* Default exit sequence for state StreetRedYellow */
	handle->stateConfVector[0] = Trafficlight_last_state;
	handle->stateConfVectorPosition = 0;
	trafficlight_exact_main_region_on_r1_StreetRedYellow(handle);
}

/* Default exit sequence for state StreetRed */
static void trafficlight_exseq_main_region_on_r1_StreetRed(Trafficlight* handle)
{
	/* Default exit sequence for state StreetRed */
	handle->stateConfVector[0] = Trafficlight_last_state;
	handle->stateConfVectorPosition = 0;
	trafficlight_exact_main_region_on_r1_StreetRed(handle);
}

/* Default exit sequence for state PedestrianGreen */
static void trafficlight_exseq_main_region_on_r1_PedestrianGreen(Trafficlight* handle)
{
	/* Default exit sequence for state PedestrianGreen */
	handle->stateConfVector[0] = Trafficlight_last_state;
	handle->stateConfVectorPosition = 0;
	trafficlight_exact_main_region_on_r1_PedestrianGreen(handle);
}

/* Default exit sequence for state PedestrianRed */
static void trafficlight_exseq_main_region_on_r1_PedestrianRed(Trafficlight* handle)
{
	/* Default exit sequence for state PedestrianRed */
	handle->stateConfVector[0] = Trafficlight_last_state;
	handle->stateConfVectorPosition = 0;
	trafficlight_exact_main_region_on_r1_PedestrianRed(handle);
}

/* Default exit sequence for state StreetAttention */
static void trafficlight_exseq_main_region_on_r1_StreetAttention(Trafficlight* handle)
{
	/* Default exit sequence for state StreetAttention */
	handle->stateConfVector[0] = Trafficlight_last_state;
	handle->stateConfVectorPosition = 0;
	trafficlight_exact_main_region_on_r1_StreetAttention(handle);
}

/* Default exit sequence for state PedestrianRequesting */
static void trafficlight_exseq_main_region_on_r1_PedestrianRequesting(Trafficlight* handle)
{
	/* Default exit sequence for state PedestrianRequesting */
	trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2(handle);
	trafficlight_exact_main_region_on_r1_PedestrianRequesting(handle);
}

/* Default exit sequence for state waitOn */
static void trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOn(Trafficlight* handle)
{
	/* Default exit sequence for state waitOn */
	handle->stateConfVector[0] = Trafficlight_last_state;
	handle->stateConfVectorPosition = 0;
	trafficlight_exact_main_region_on_r1_PedestrianRequesting_r2_waitOn(handle);
}

/* Default exit sequence for state waitOff */
static void trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOff(Trafficlight* handle)
{
	/* Default exit sequence for state waitOff */
	handle->stateConfVector[0] = Trafficlight_last_state;
	handle->stateConfVectorPosition = 0;
	trafficlight_exact_main_region_on_r1_PedestrianRequesting_r2_waitOff(handle);
}

/* Default exit sequence for state off */
static void trafficlight_exseq_main_region_off(Trafficlight* handle)
{
	/* Default exit sequence for state off */
	trafficlight_exseq_main_region_off_r1(handle);
}

/* Default exit sequence for state YellowOn */
static void trafficlight_exseq_main_region_off_r1_YellowOn(Trafficlight* handle)
{
	/* Default exit sequence for state YellowOn */
	handle->stateConfVector[0] = Trafficlight_last_state;
	handle->stateConfVectorPosition = 0;
	trafficlight_exact_main_region_off_r1_YellowOn(handle);
}

/* Default exit sequence for state YellowOff */
static void trafficlight_exseq_main_region_off_r1_YellowOff(Trafficlight* handle)
{
	/* Default exit sequence for state YellowOff */
	handle->stateConfVector[0] = Trafficlight_last_state;
	handle->stateConfVectorPosition = 0;
	trafficlight_exact_main_region_off_r1_YellowOff(handle);
}

/* Default exit sequence for region main region */
static void trafficlight_exseq_main_region(Trafficlight* handle)
{
	/* Default exit sequence for region main region */
	/* Handle exit of all possible states (of Trafficlight.main_region) at position 0... */
	switch(handle->stateConfVector[ 0 ])
	{
		case Trafficlight_main_region_on_r1_StreetGreen :
		{
			trafficlight_exseq_main_region_on_r1_StreetGreen(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_StreetRedYellow :
		{
			trafficlight_exseq_main_region_on_r1_StreetRedYellow(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_StreetRed :
		{
			trafficlight_exseq_main_region_on_r1_StreetRed(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianGreen :
		{
			trafficlight_exseq_main_region_on_r1_PedestrianGreen(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianRed :
		{
			trafficlight_exseq_main_region_on_r1_PedestrianRed(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_StreetAttention :
		{
			trafficlight_exseq_main_region_on_r1_StreetAttention(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn :
		{
			trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOn(handle);
			trafficlight_exact_main_region_on_r1_PedestrianRequesting(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff :
		{
			trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOff(handle);
			trafficlight_exact_main_region_on_r1_PedestrianRequesting(handle);
			break;
		}
		case Trafficlight_main_region_off_r1_YellowOn :
		{
			trafficlight_exseq_main_region_off_r1_YellowOn(handle);
			break;
		}
		case Trafficlight_main_region_off_r1_YellowOff :
		{
			trafficlight_exseq_main_region_off_r1_YellowOff(handle);
			break;
		}
		default: break;
	}
}

/* Default exit sequence for region r1 */
static void trafficlight_exseq_main_region_on_r1(Trafficlight* handle)
{
	/* Default exit sequence for region r1 */
	/* Handle exit of all possible states (of Trafficlight.main_region.on.r1) at position 0... */
	switch(handle->stateConfVector[ 0 ])
	{
		case Trafficlight_main_region_on_r1_StreetGreen :
		{
			trafficlight_exseq_main_region_on_r1_StreetGreen(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_StreetRedYellow :
		{
			trafficlight_exseq_main_region_on_r1_StreetRedYellow(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_StreetRed :
		{
			trafficlight_exseq_main_region_on_r1_StreetRed(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianGreen :
		{
			trafficlight_exseq_main_region_on_r1_PedestrianGreen(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianRed :
		{
			trafficlight_exseq_main_region_on_r1_PedestrianRed(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_StreetAttention :
		{
			trafficlight_exseq_main_region_on_r1_StreetAttention(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn :
		{
			trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOn(handle);
			trafficlight_exact_main_region_on_r1_PedestrianRequesting(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff :
		{
			trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOff(handle);
			trafficlight_exact_main_region_on_r1_PedestrianRequesting(handle);
			break;
		}
		default: break;
	}
}

/* Default exit sequence for region r2 */
static void trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2(Trafficlight* handle)
{
	/* Default exit sequence for region r2 */
	/* Handle exit of all possible states (of Trafficlight.main_region.on.r1.PedestrianRequesting.r2) at position 0... */
	switch(handle->stateConfVector[ 0 ])
	{
		case Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn :
		{
			trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOn(handle);
			break;
		}
		case Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff :
		{
			trafficlight_exseq_main_region_on_r1_PedestrianRequesting_r2_waitOff(handle);
			break;
		}
		default: break;
	}
}

/* Default exit sequence for region r1 */
static void trafficlight_exseq_main_region_off_r1(Trafficlight* handle)
{
	/* Default exit sequence for region r1 */
	/* Handle exit of all possible states (of Trafficlight.main_region.off.r1) at position 0... */
	switch(handle->stateConfVector[ 0 ])
	{
		case Trafficlight_main_region_off_r1_YellowOn :
		{
			trafficlight_exseq_main_region_off_r1_YellowOn(handle);
			break;
		}
		case Trafficlight_main_region_off_r1_YellowOff :
		{
			trafficlight_exseq_main_region_off_r1_YellowOff(handle);
			break;
		}
		default: break;
	}
}

/* The reactions of state StreetGreen. */
static void trafficlight_react_main_region_on_r1_StreetGreen(Trafficlight* handle)
{
	/* The reactions of state StreetGreen. */
	if (trafficlight_check_main_region_on_tr0_tr0(handle) == bool_true)
	{ 
		trafficlight_effect_main_region_on_tr0(handle);
	}  else
	{
		if (trafficlight_check_main_region_on_r1_StreetGreen_tr0_tr0(handle) == bool_true)
		{ 
			trafficlight_effect_main_region_on_r1_StreetGreen_tr0(handle);
		} 
	}
}

/* The reactions of state StreetRedYellow. */
static void trafficlight_react_main_region_on_r1_StreetRedYellow(Trafficlight* handle)
{
	/* The reactions of state StreetRedYellow. */
	if (trafficlight_check_main_region_on_tr0_tr0(handle) == bool_true)
	{ 
		trafficlight_effect_main_region_on_tr0(handle);
	}  else
	{
		if (trafficlight_check_main_region_on_r1_StreetRedYellow_tr0_tr0(handle) == bool_true)
		{ 
			trafficlight_effect_main_region_on_r1_StreetRedYellow_tr0(handle);
		} 
	}
}

/* The reactions of state StreetRed. */
static void trafficlight_react_main_region_on_r1_StreetRed(Trafficlight* handle)
{
	/* The reactions of state StreetRed. */
	if (trafficlight_check_main_region_on_tr0_tr0(handle) == bool_true)
	{ 
		trafficlight_effect_main_region_on_tr0(handle);
	}  else
	{
		if (trafficlight_check_main_region_on_r1_StreetRed_tr0_tr0(handle) == bool_true)
		{ 
			trafficlight_effect_main_region_on_r1_StreetRed_tr0(handle);
		} 
	}
}

/* The reactions of state PedestrianGreen. */
static void trafficlight_react_main_region_on_r1_PedestrianGreen(Trafficlight* handle)
{
	/* The reactions of state PedestrianGreen. */
	if (trafficlight_check_main_region_on_tr0_tr0(handle) == bool_true)
	{ 
		trafficlight_effect_main_region_on_tr0(handle);
	}  else
	{
		if (trafficlight_check_main_region_on_r1_PedestrianGreen_tr0_tr0(handle) == bool_true)
		{ 
			trafficlight_effect_main_region_on_r1_PedestrianGreen_tr0(handle);
		} 
	}
}

/* The reactions of state PedestrianRed. */
static void trafficlight_react_main_region_on_r1_PedestrianRed(Trafficlight* handle)
{
	/* The reactions of state PedestrianRed. */
	if (trafficlight_check_main_region_on_tr0_tr0(handle) == bool_true)
	{ 
		trafficlight_effect_main_region_on_tr0(handle);
	}  else
	{
		if (trafficlight_check_main_region_on_r1_PedestrianRed_tr0_tr0(handle) == bool_true)
		{ 
			trafficlight_effect_main_region_on_r1_PedestrianRed_tr0(handle);
		} 
	}
}

/* The reactions of state StreetAttention. */
static void trafficlight_react_main_region_on_r1_StreetAttention(Trafficlight* handle)
{
	/* The reactions of state StreetAttention. */
	if (trafficlight_check_main_region_on_tr0_tr0(handle) == bool_true)
	{ 
		trafficlight_effect_main_region_on_tr0(handle);
	}  else
	{
		if (trafficlight_check_main_region_on_r1_StreetAttention_tr0_tr0(handle) == bool_true)
		{ 
			trafficlight_effect_main_region_on_r1_StreetAttention_tr0(handle);
		} 
	}
}

/* The reactions of state waitOn. */
static void trafficlight_react_main_region_on_r1_PedestrianRequesting_r2_waitOn(Trafficlight* handle)
{
	/* The reactions of state waitOn. */
	if (trafficlight_check_main_region_on_tr0_tr0(handle) == bool_true)
	{ 
		trafficlight_effect_main_region_on_tr0(handle);
	}  else
	{
		if (trafficlight_check_main_region_on_r1_PedestrianRequesting_tr0_tr0(handle) == bool_true)
		{ 
			trafficlight_effect_main_region_on_r1_PedestrianRequesting_tr0(handle);
		}  else
		{
			if (trafficlight_check_main_region_on_r1_PedestrianRequesting_r2_waitOn_tr0_tr0(handle) == bool_true)
			{ 
				trafficlight_effect_main_region_on_r1_PedestrianRequesting_r2_waitOn_tr0(handle);
			} 
		}
	}
}

/* The reactions of state waitOff. */
static void trafficlight_react_main_region_on_r1_PedestrianRequesting_r2_waitOff(Trafficlight* handle)
{
	/* The reactions of state waitOff. */
	if (trafficlight_check_main_region_on_tr0_tr0(handle) == bool_true)
	{ 
		trafficlight_effect_main_region_on_tr0(handle);
	}  else
	{
		if (trafficlight_check_main_region_on_r1_PedestrianRequesting_tr0_tr0(handle) == bool_true)
		{ 
			trafficlight_effect_main_region_on_r1_PedestrianRequesting_tr0(handle);
		}  else
		{
			if (trafficlight_check_main_region_on_r1_PedestrianRequesting_r2_waitOff_tr0_tr0(handle) == bool_true)
			{ 
				trafficlight_effect_main_region_on_r1_PedestrianRequesting_r2_waitOff_tr0(handle);
			} 
		}
	}
}

/* The reactions of state YellowOn. */
static void trafficlight_react_main_region_off_r1_YellowOn(Trafficlight* handle)
{
	/* The reactions of state YellowOn. */
	if (trafficlight_check_main_region_off_tr0_tr0(handle) == bool_true)
	{ 
		trafficlight_effect_main_region_off_tr0(handle);
	}  else
	{
		if (trafficlight_check_main_region_off_r1_YellowOn_tr0_tr0(handle) == bool_true)
		{ 
			trafficlight_effect_main_region_off_r1_YellowOn_tr0(handle);
		} 
	}
}

/* The reactions of state YellowOff. */
static void trafficlight_react_main_region_off_r1_YellowOff(Trafficlight* handle)
{
	/* The reactions of state YellowOff. */
	if (trafficlight_check_main_region_off_tr0_tr0(handle) == bool_true)
	{ 
		trafficlight_effect_main_region_off_tr0(handle);
	}  else
	{
		if (trafficlight_check_main_region_off_r1_YellowOff_tr0_tr0(handle) == bool_true)
		{ 
			trafficlight_effect_main_region_off_r1_YellowOff_tr0(handle);
		} 
	}
}

/* Default react sequence for initial entry  */
static void trafficlight_react_main_region_on_r1__entry_Default(Trafficlight* handle)
{
	/* Default react sequence for initial entry  */
	trafficlight_enseq_main_region_on_r1_PedestrianRed_default(handle);
}

/* Default react sequence for initial entry  */
static void trafficlight_react_main_region_on_r1_PedestrianRequesting_r2__entry_Default(Trafficlight* handle)
{
	/* Default react sequence for initial entry  */
	trafficlight_enseq_main_region_on_r1_PedestrianRequesting_r2_waitOn_default(handle);
}

/* Default react sequence for initial entry  */
static void trafficlight_react_main_region__entry_Default(Trafficlight* handle)
{
	/* Default react sequence for initial entry  */
	trafficlight_enseq_main_region_on_default(handle);
}

/* Default react sequence for initial entry  */
static void trafficlight_react_main_region_off_r1__entry_Default(Trafficlight* handle)
{
	/* Default react sequence for initial entry  */
	trafficlight_enseq_main_region_off_r1_YellowOn_default(handle);
}


