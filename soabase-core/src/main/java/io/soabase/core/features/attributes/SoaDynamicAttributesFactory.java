/**
 * Copyright 2014 Jordan Zimmerman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.soabase.core.features.attributes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.jackson.Discoverable;
import io.dropwizard.setup.Environment;
import io.soabase.core.SoaConfiguration;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = NullDynamicAttributesFactory.class)
public interface SoaDynamicAttributesFactory extends Discoverable
{
    public SoaDynamicAttributes build(Environment environment, List<String> scopes);
}
