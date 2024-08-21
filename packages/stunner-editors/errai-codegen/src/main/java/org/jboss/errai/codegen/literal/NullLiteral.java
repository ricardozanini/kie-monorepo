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


package org.jboss.errai.codegen.literal;

import org.jboss.errai.codegen.Context;
import org.jboss.errai.codegen.meta.MetaClass;
import org.jboss.errai.codegen.meta.MetaClassFactory;
import org.mvel2.util.NullType;

/**
 * @author Mike Brock <cbrock@redhat.com>
 */
public class NullLiteral extends LiteralValue<Object> {
  public static final NullLiteral INSTANCE = new NullLiteral();

  private NullLiteral() {
    super(null);
  }

  @Override
  public String getCanonicalString(final Context context) {
    return "null";
  }

  @Override
  public MetaClass getType() {
    return MetaClassFactory.get(NullType.class);
  }
}
