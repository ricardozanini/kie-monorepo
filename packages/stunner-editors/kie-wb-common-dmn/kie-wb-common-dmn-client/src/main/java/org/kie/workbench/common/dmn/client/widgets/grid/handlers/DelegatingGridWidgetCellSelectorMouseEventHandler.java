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

package org.kie.workbench.common.dmn.client.widgets.grid.handlers;

import java.util.Optional;
import java.util.function.Supplier;

import com.ait.lienzo.client.core.event.AbstractNodeHumanInputEvent;
import com.ait.lienzo.client.core.types.Point2D;
import org.kie.workbench.common.dmn.client.widgets.grid.BaseExpressionGrid;
import org.kie.workbench.common.dmn.client.widgets.grid.model.GridCellTuple;
import org.uberfire.ext.wires.core.grids.client.widget.grid.GridWidget;
import org.uberfire.ext.wires.core.grids.client.widget.grid.impl.DefaultGridWidgetCellSelectorMouseEventHandler;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridSelectionManager;

public class DelegatingGridWidgetCellSelectorMouseEventHandler extends DefaultGridWidgetCellSelectorMouseEventHandler {

    private final Supplier<GridCellTuple> parentSupplier;
    private final Supplier<Integer> nestingSupplier;

    public DelegatingGridWidgetCellSelectorMouseEventHandler(final GridSelectionManager selectionManager,
                                                             final Supplier<GridCellTuple> parentSupplier,
                                                             final Supplier<Integer> nestingSupplier) {
        super(selectionManager);
        this.parentSupplier = parentSupplier;
        this.nestingSupplier = nestingSupplier;
    }

    @Override
    public boolean onNodeMouseEvent(final GridWidget gridWidget,
                                    final Point2D relativeLocation,
                                    final Optional<Integer> uiHeaderRowIndex,
                                    final Optional<Integer> uiHeaderColumnIndex,
                                    final Optional<Integer> uiRowIndex,
                                    final Optional<Integer> uiColumnIndex,
                                    final AbstractNodeHumanInputEvent event) {
        if (nestingSupplier.get() == 0) {
            return doSuperOnNodeMouseEvent(gridWidget,
                                           relativeLocation,
                                           uiHeaderRowIndex,
                                           uiHeaderColumnIndex,
                                           uiRowIndex,
                                           uiColumnIndex,
                                           event);
        }

        boolean isHandled = false;
        if (uiRowIndex.isPresent() && uiColumnIndex.isPresent()) {
            isHandled = delegatedHandleBodyCell(gridWidget,
                                                uiRowIndex.get(),
                                                uiColumnIndex.get(),
                                                event);
        }
        if (isHandled) {
            final GridWidget parentGridWidget = parentSupplier.get().getGridWidget();
            if (!parentGridWidget.isSelected()) {
                selectionManager.select(parentGridWidget);
            }
        }

        return isHandled;
    }

    boolean doSuperOnNodeMouseEvent(final GridWidget gridWidget,
                                    final Point2D relativeLocation,
                                    final Optional<Integer> uiHeaderRowIndex,
                                    final Optional<Integer> uiHeaderColumnIndex,
                                    final Optional<Integer> uiRowIndex,
                                    final Optional<Integer> uiColumnIndex,
                                    final AbstractNodeHumanInputEvent event) {
        return super.onNodeMouseEvent(gridWidget,
                                      relativeLocation,
                                      uiHeaderRowIndex,
                                      uiHeaderColumnIndex,
                                      uiRowIndex,
                                      uiColumnIndex,
                                      event);
    }

    private boolean delegatedHandleBodyCell(final GridWidget gridWidget,
                                            final int uiRowIndex,
                                            final int uiColumnIndex,
                                            final AbstractNodeHumanInputEvent event) {
        final GridCellTuple parent = parentSupplier.get();
        final GridWidget parentGridWidget = parent.getGridWidget();
        final int parentRowIndex = parent.getRowIndex();
        final int parentColumnIndex = parent.getColumnIndex();

        //Selection of a parent GridWidget cell is handled by CellSelectionManager to avoid possible calls
        //to the parent's BaseExpressionGrid.doAfterSelectionChange() method to refresh the Properties Panel...
        final boolean isSelectionChanged = parentGridWidget.getCellSelectionManager().selectCell(parentRowIndex,
                                                                                                 parentColumnIndex,
                                                                                                 event.isShiftKeyDown(),
                                                                                                 event.isCtrlKeyDown());

        if (isSelectionChanged) {
            parentGridWidget.getLayer().batch();
        }

        //...therefore ensure the correct invocation is called.
        if (gridWidget instanceof BaseExpressionGrid) {
            ((BaseExpressionGrid) gridWidget).doAfterSelectionChange(uiRowIndex, uiColumnIndex);
        }
        return isSelectionChanged;
    }
}
