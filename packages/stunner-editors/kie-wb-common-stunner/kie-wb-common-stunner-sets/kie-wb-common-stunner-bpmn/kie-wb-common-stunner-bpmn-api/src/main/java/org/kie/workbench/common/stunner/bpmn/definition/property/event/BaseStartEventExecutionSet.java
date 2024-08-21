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


package org.kie.workbench.common.stunner.bpmn.definition.property.event;

import java.util.Objects;

import javax.validation.Valid;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.workbench.common.forms.adf.definitions.annotations.FormDefinition;
import org.kie.workbench.common.forms.adf.definitions.annotations.FormField;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.checkBox.type.CheckBoxFieldType;
import org.kie.workbench.common.stunner.bpmn.definition.BPMNPropertySet;
import org.kie.workbench.common.stunner.bpmn.definition.property.general.SLADueDate;
import org.kie.workbench.common.stunner.core.definition.annotation.Property;
import org.kie.workbench.common.stunner.core.util.HashUtil;

@Portable
@Bindable
@FormDefinition(startElement = "isInterrupting")
public class BaseStartEventExecutionSet implements BPMNPropertySet {

    @Property
    @FormField(type = CheckBoxFieldType.class)
    @Valid
    private IsInterrupting isInterrupting;

    @Property
    @FormField(afterElement = "isInterrupting")
    @Valid
    private SLADueDate slaDueDate;

    public BaseStartEventExecutionSet() {
        this(new IsInterrupting(),
             new SLADueDate());
    }

    public BaseStartEventExecutionSet(final @MapsTo("isInterrupting") IsInterrupting isInterrupting,
                                      final @MapsTo("slaDueDate") SLADueDate slaDueDate) {
        this.isInterrupting = isInterrupting;
        this.slaDueDate = slaDueDate;
    }

    public IsInterrupting getIsInterrupting() {
        return isInterrupting;
    }

    public void setIsInterrupting(IsInterrupting isInterrupting) {
        this.isInterrupting = isInterrupting;
    }

    public SLADueDate getSlaDueDate() {
        return slaDueDate;
    }

    public void setSlaDueDate(SLADueDate slaDueDate) {
        this.slaDueDate = slaDueDate;
    }

    @Override
    public int hashCode() {
        return HashUtil.combineHashCodes(Objects.hashCode(isInterrupting),
                                         Objects.hashCode(slaDueDate));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BaseStartEventExecutionSet) {
            BaseStartEventExecutionSet other = (BaseStartEventExecutionSet) o;
            return Objects.equals(isInterrupting, other.isInterrupting) &&
                    Objects.equals(slaDueDate, other.slaDueDate);
        }
        return false;
    }
}
