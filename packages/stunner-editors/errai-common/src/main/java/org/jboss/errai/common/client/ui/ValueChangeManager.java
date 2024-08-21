/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License. 
 */


package org.jboss.errai.common.client.ui;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * Utility class for managing widgets implementing {@link HasValue}.
 *
 * @author Max Barkley <mbarkley@redhat.com>
 */
public class ValueChangeManager<T, W extends Widget & HasValue<T>> implements HasValueChangeHandlers<T> {

  private boolean valueChangeHandlerInitialized;
  private final W widget;

  public ValueChangeManager(final W widget) {
    this.widget = widget;
  }

  @Override
  public void fireEvent(final GwtEvent<?> event) {
    widget.fireEvent(event);
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> handler) {
    if (!valueChangeHandlerInitialized) {
      valueChangeHandlerInitialized = true;
      widget.addDomHandler(new ChangeHandler() {
        @Override
        public void onChange(ChangeEvent event) {
          ValueChangeEvent.fire(widget, widget.getValue());
        }
      }, ChangeEvent.getType());
    }
    return widget.addHandler(handler, ValueChangeEvent.getType());
  }

}
