/*
 * Copyright 2017-present Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.cli.net;

import org.apache.karaf.shell.commands.Command;
import org.onosproject.cli.AbstractShellCommand;
import org.onosproject.incubator.net.routing.RouteStore;

/**
 * Command to show the current route store implementation.
 */
@Command(scope = "onos", name = "route-store",
        description = "Show the current route store implementation.")
public class RouteStoreCommand extends AbstractShellCommand {

    @Override
    protected void execute() {
        RouteStore routeStore = AbstractShellCommand.get(RouteStore.class);
        print(routeStore.name());
    }
}