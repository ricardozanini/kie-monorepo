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


package org.kie.workbench.common.stunner.forms.service.fieldProviders;

import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.BasicTypeFieldProvider;
import org.kie.workbench.common.forms.model.TypeInfo;
import org.kie.workbench.common.stunner.forms.model.ColorPickerFieldDefinition;
import org.kie.workbench.common.stunner.forms.model.ColorPickerFieldType;

public class ColorPickerFieldProvider extends BasicTypeFieldProvider<ColorPickerFieldDefinition> {

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    protected void doRegisterFields() {
        registerPropertyType(String.class);
    }

    @Override
    public ColorPickerFieldDefinition createFieldByType(TypeInfo typeInfo) {
        return getDefaultField();
    }

    @Override
    public Class<ColorPickerFieldType> getFieldType() {
        return ColorPickerFieldType.class;
    }

    @Override
    public String getFieldTypeName() {
        return ColorPickerFieldDefinition.FIELD_TYPE.getTypeName();
    }

    @Override
    public ColorPickerFieldDefinition getDefaultField() {
        return new ColorPickerFieldDefinition();
    }
}
