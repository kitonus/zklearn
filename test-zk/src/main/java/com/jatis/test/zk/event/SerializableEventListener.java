package com.jatis.test.zk.event;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public interface SerializableEventListener<T extends Event> extends EventListener<T>{

}
