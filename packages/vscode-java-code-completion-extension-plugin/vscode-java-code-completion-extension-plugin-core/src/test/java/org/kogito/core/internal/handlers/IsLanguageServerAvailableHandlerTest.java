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

package org.kogito.core.internal.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kogito.core.internal.engine.ActivationChecker;
import org.kogito.core.internal.util.TestUtil;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kogito.core.internal.util.TestUtil.COMMON_RESOURCE_PATH;

class IsLanguageServerAvailableHandlerTest {

    private ActivationChecker activationChecker;
    private IsLanguageServerAvailableHandler isLanguageServerAvailableHandler;

    @BeforeEach
    public void setup() {
        activationChecker = new ActivationChecker();
        isLanguageServerAvailableHandler = new IsLanguageServerAvailableHandler(HandlerConstants.IS_AVAILABLE, activationChecker);
    }

    @Test
    void isLanguageServerNotAvailable() {
        TestUtil.mockWorkspace(COMMON_RESOURCE_PATH +  "noActivatorProject" + File.separator, () -> {
            activationChecker.check();
            assertThat(isLanguageServerAvailableHandler.handle(null, null)).isFalse();
        });
    }

    @Test
    void isLanguageServerAvailable() {
        TestUtil.mockWorkspace(COMMON_RESOURCE_PATH +  "testProject" + File.separator, () -> {
            activationChecker.check();
            assertThat(isLanguageServerAvailableHandler.handle(null, null)).isTrue();
        });
    }
}
