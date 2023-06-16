/*
 * Copyright 2017-present Open Networking Foundation
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
package org.onosproject.net.topology;

import static org.onosproject.net.Link.State.ACTIVE;
import static org.onosproject.net.Link.Type.INDIRECT;

//import org.onosproject.store.device.impl.GossipDeviceStore;

import org.onlab.graph.ScalarWeight;
import org.onlab.graph.Weight;
import org.slf4j.Logger; // joo
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.onosproject.net.DefaultAnnotations.Builder;
import org.onosproject.net.DeviceId;
import org.onosproject.net.AbstractDescription;
import org.onosproject.net.DefaultAnnotations;
import org.onosproject.net.PortNumber;
import org.onosproject.net.Port;
import org.onosproject.net.SparseAnnotations;
import org.onosproject.net.device.DefaultPortDescription;
import org.onosproject.net.device.DefaultDeviceDescription;
import org.onosproject.net.device.DeviceDescription;
import org.onosproject.net.device.PortDescription;
import org.onosproject.net.SparseAnnotations;
import org.onosproject.net.device.DeviceProvider;
import org.onosproject.net.provider.AbstractProvider;
//import org.onosproject.provider.of.device.impl.OpenFlowDeviceProvider;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.onosproject.net.Port.Type;

import static org.slf4j.LoggerFactory.getLogger; //joo

/**
 * Link weight for measuring link cost as hop count with indirect links
 * being as expensive as traversing the entire graph to assume the worst.
 */
public class HopCountLinkWeigher implements LinkWeigher {

    private final Logger log = getLogger(getClass());
    public static HashMap<String, Long> pointtospeed = new HashMap<String, Long>();



    public static final LinkWeigher DEFAULT_HOP_COUNT_WEIGHER = new HopCountLinkWeigher();

    private static final ScalarWeight ZERO = new ScalarWeight(0.0);
    private static final ScalarWeight ONE = new ScalarWeight(1.0);
    private static final ScalarWeight DEFAULT_INDIRECT = new ScalarWeight(Short.MAX_VALUE);


    private final ScalarWeight indirectLinkCost;


    /**
     * Creates a new hop-count weight.
     */
    public HopCountLinkWeigher() {
        this.indirectLinkCost = DEFAULT_INDIRECT;
    }

    /**
     * Creates a new hop-count weight with the specified cost of indirect links.
     *
     * @param indirectLinkCost indirect link cost
     */
    public HopCountLinkWeigher(double indirectLinkCost) {
        this.indirectLinkCost = new ScalarWeight(indirectLinkCost);
    }

    // final OpenFlowDeviceProvider openflowdeviceproviderforportspeed = new OpenFlowDeviceProvider();

    @Override
    public Weight weight(TopologyEdge edge) {
        //log.info("joo) Using HopCountLinkWeigher TopologeEdge edge is {}",edge);
        //log.info("joo) edge {} , src {}, speed {}", edge, edge.link().src(), edge); //openflowdeviceproviderforportspeed.portnumbertospeedmap.get(edge.link().src());
        Long tempweight = (long)1; //pointtospeed.get();//edge.link().src());
        log.info("joo) HopCountLinkWeigher edge.link().src() {} ", edge.link().src().toString());
        if (edge.link().state() == ACTIVE) {
            
            return edge.link().type() == INDIRECT ? indirectLinkCost : ScalarWeight.toWeight((double)tempweight);//ONE;
        } else {
            return getNonViableWeight();
        }
    }

    @Override
    public Weight getInitialWeight() {
        return ZERO;
    }

    @Override
    public Weight getNonViableWeight() {
        return ScalarWeight.NON_VIABLE_WEIGHT;


    }   

    public static void SpeedCountLinkWeigher(String src, Long portspeed) {
        pointtospeed.put(src, portspeed);
    }


}
