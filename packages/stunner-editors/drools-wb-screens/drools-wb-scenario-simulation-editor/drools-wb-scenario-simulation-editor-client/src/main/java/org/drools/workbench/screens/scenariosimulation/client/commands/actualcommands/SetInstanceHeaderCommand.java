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

package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import org.drools.scenariosimulation.api.model.FactMappingValueType;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;

/**
 * <code>Command</code> to set the <i>instance</i> level header for a given column
 */
public class SetInstanceHeaderCommand extends AbstractSelectedColumnCommand {

    public SetInstanceHeaderCommand(GridWidget gridWidget) {
        super(gridWidget, FactMappingValueType.NOT_EXPRESSION);
    }

    @Override
    protected void executeIfSelectedColumn(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn) {
        setInstanceHeader(context, selectedColumn, context.getStatus().getClassName(), getFullPackage(context) + context.getStatus().getClassName());
    }
}
