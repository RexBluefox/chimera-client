package dev.chimera;

import dev.chimera.amalthea.EventBus;
import dev.chimera.amalthea.events.EventSystemTest;
import dev.chimera.amalthea.events.misc.TickEvent;
import dev.chimera.modules.ModuleInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChimeraClient implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("chimera-client");
	public static final EventBus EVENT_BUS = new EventBus();
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//TODO clean up this class. we probably shouldn't be doing everything right here
		LOGGER.info("Hello Chimera sussers!");

		EventSystemTest test = new EventSystemTest();

		EVENT_BUS.postEvent("Systems operational?");

		EVENT_BUS.postEventToListener("idPush", EventListeners.a);


		new ModuleInitializer().initializeModules();

		TickEvent.Start tickEventStart = new TickEvent.Start();
		ClientTickEvents.START_CLIENT_TICK.register((startTick) -> {
				EVENT_BUS.postEvent(tickEventStart);
		});

		TickEvent.End tickEventEnd = new TickEvent.End();
		ClientTickEvents.END_CLIENT_TICK.register((endTick) -> {
				EVENT_BUS.postEvent(tickEventEnd);
		});

	}

	public Event<?> event = ScreenEvents.AFTER_INIT;
}