
#ifndef TRAFFICLIGHT_H_
#define TRAFFICLIGHT_H_

#include "sc_types.h"
		
#ifdef __cplusplus
extern "C" { 
#endif 

/*! \file Header of the state machine 'Trafficlight'.
*/

/*! Enumeration of all states */ 
typedef enum
{
	Trafficlight_main_region_on,
	Trafficlight_main_region_on_r1_StreetGreen,
	Trafficlight_main_region_on_r1_StreetRedYellow,
	Trafficlight_main_region_on_r1_StreetRed,
	Trafficlight_main_region_on_r1_PedestrianGreen,
	Trafficlight_main_region_on_r1_PedestrianRed,
	Trafficlight_main_region_on_r1_StreetAttention,
	Trafficlight_main_region_on_r1_PedestrianRequesting,
	Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn,
	Trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff,
	Trafficlight_main_region_off,
	Trafficlight_main_region_off_r1_YellowOn,
	Trafficlight_main_region_off_r1_YellowOff,
	Trafficlight_last_state
} TrafficlightStates;

/*! Type definition of the data structure for the TrafficlightIfaceTrafficLight interface scope. */
typedef struct
{
	sc_boolean red;
	sc_boolean yellow;
	sc_boolean green;
} TrafficlightIfaceTrafficLight;

/*! Type definition of the data structure for the TrafficlightIfacePedestrianLight interface scope. */
typedef struct
{
	sc_boolean red;
	sc_boolean green;
	sc_boolean wait;
} TrafficlightIfacePedestrianLight;

/*! Type definition of the data structure for the TrafficlightIface interface scope. */
typedef struct
{
	sc_boolean pedestrianRequest_raised;
	sc_boolean onOff_raised;
} TrafficlightIface;

/*! Type definition of the data structure for the TrafficlightTimeEvents interface scope. */
typedef struct
{
	sc_boolean trafficlight_main_region_on_r1_StreetRedYellow_tev0_raised;
	sc_boolean trafficlight_main_region_on_r1_StreetRed_tev0_raised;
	sc_boolean trafficlight_main_region_on_r1_PedestrianGreen_tev0_raised;
	sc_boolean trafficlight_main_region_on_r1_PedestrianRed_tev0_raised;
	sc_boolean trafficlight_main_region_on_r1_StreetAttention_tev0_raised;
	sc_boolean trafficlight_main_region_on_r1_PedestrianRequesting_tev0_raised;
	sc_boolean trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOn_tev0_raised;
	sc_boolean trafficlight_main_region_on_r1_PedestrianRequesting_r2_waitOff_tev0_raised;
	sc_boolean trafficlight_main_region_off_r1_YellowOn_tev0_raised;
	sc_boolean trafficlight_main_region_off_r1_YellowOff_tev0_raised;
} TrafficlightTimeEvents;


/*! Define dimension of the state configuration vector for orthogonal states. */
#define TRAFFICLIGHT_MAX_ORTHOGONAL_STATES 1

/*! 
 * Type definition of the data structure for the Trafficlight state machine.
 * This data structure has to be allocated by the client code. 
 */
typedef struct
{
	TrafficlightStates stateConfVector[TRAFFICLIGHT_MAX_ORTHOGONAL_STATES];
	sc_ushort stateConfVectorPosition; 
	
	TrafficlightIfaceTrafficLight ifaceTrafficLight;
	TrafficlightIfacePedestrianLight ifacePedestrianLight;
	TrafficlightIface iface;
	TrafficlightTimeEvents timeEvents;
} Trafficlight;

/*! Initializes the Trafficlight state machine data structures. Must be called before first usage.*/
extern void trafficlight_init(Trafficlight* handle);

/*! Activates the state machine */
extern void trafficlight_enter(Trafficlight* handle);

/*! Deactivates the state machine */
extern void trafficlight_exit(Trafficlight* handle);

/*! Performs a 'run to completion' step. */
extern void trafficlight_runCycle(Trafficlight* handle);

/*! Raises a time event. */
extern void trafficlight_raiseTimeEvent(const Trafficlight* handle, sc_eventid evid);

/*! Gets the value of the variable 'red' that is defined in the interface scope 'TrafficLight'. */ 
extern sc_boolean trafficlightIfaceTrafficLight_get_red(const Trafficlight* handle);
/*! Sets the value of the variable 'red' that is defined in the interface scope 'TrafficLight'. */ 
extern void trafficlightIfaceTrafficLight_set_red(Trafficlight* handle, sc_boolean value);
/*! Gets the value of the variable 'yellow' that is defined in the interface scope 'TrafficLight'. */ 
extern sc_boolean trafficlightIfaceTrafficLight_get_yellow(const Trafficlight* handle);
/*! Sets the value of the variable 'yellow' that is defined in the interface scope 'TrafficLight'. */ 
extern void trafficlightIfaceTrafficLight_set_yellow(Trafficlight* handle, sc_boolean value);
/*! Gets the value of the variable 'green' that is defined in the interface scope 'TrafficLight'. */ 
extern sc_boolean trafficlightIfaceTrafficLight_get_green(const Trafficlight* handle);
/*! Sets the value of the variable 'green' that is defined in the interface scope 'TrafficLight'. */ 
extern void trafficlightIfaceTrafficLight_set_green(Trafficlight* handle, sc_boolean value);
/*! Gets the value of the variable 'red' that is defined in the interface scope 'PedestrianLight'. */ 
extern sc_boolean trafficlightIfacePedestrianLight_get_red(const Trafficlight* handle);
/*! Sets the value of the variable 'red' that is defined in the interface scope 'PedestrianLight'. */ 
extern void trafficlightIfacePedestrianLight_set_red(Trafficlight* handle, sc_boolean value);
/*! Gets the value of the variable 'green' that is defined in the interface scope 'PedestrianLight'. */ 
extern sc_boolean trafficlightIfacePedestrianLight_get_green(const Trafficlight* handle);
/*! Sets the value of the variable 'green' that is defined in the interface scope 'PedestrianLight'. */ 
extern void trafficlightIfacePedestrianLight_set_green(Trafficlight* handle, sc_boolean value);
/*! Gets the value of the variable 'wait' that is defined in the interface scope 'PedestrianLight'. */ 
extern sc_boolean trafficlightIfacePedestrianLight_get_wait(const Trafficlight* handle);
/*! Sets the value of the variable 'wait' that is defined in the interface scope 'PedestrianLight'. */ 
extern void trafficlightIfacePedestrianLight_set_wait(Trafficlight* handle, sc_boolean value);
/*! Raises the in event 'pedestrianRequest' that is defined in the default interface scope. */ 
extern void trafficlightIface_raise_pedestrianRequest(Trafficlight* handle);

/*! Raises the in event 'onOff' that is defined in the default interface scope. */ 
extern void trafficlightIface_raise_onOff(Trafficlight* handle);


/*!
 * Checks whether the state machine is active (until 2.4.1 this method was used for states).
 * A state machine is active if it was entered. It is inactive if it has not been entered at all or if it has been exited.
 */
extern sc_boolean trafficlight_isActive(const Trafficlight* handle);

/*!
 * Checks if all active states are final. 
 * If there are no active states then the state machine is considered being inactive. In this case this method returns false.
 */
extern sc_boolean trafficlight_isFinal(const Trafficlight* handle);

/*! Checks if the specified state is active (until 2.4.1 the used method for states was called isActive()). */
extern sc_boolean trafficlight_isStateActive(const Trafficlight* handle, TrafficlightStates state);

#ifdef __cplusplus
}
#endif 

#endif /* TRAFFICLIGHT_H_ */
