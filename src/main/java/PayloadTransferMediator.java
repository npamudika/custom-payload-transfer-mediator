package com.custom;

import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

/**
 * Payload Transfer Mediator Implementation.
 */
public class PayloadTransferMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext context) {
        org.apache.axis2.context.MessageContext axis2MessageContext = ((Axis2MessageContext) context)
                .getAxis2MessageContext();

        String payload;
        if (JsonUtil.hasAJsonPayload(axis2MessageContext)) {
            payload = JsonUtil.jsonPayloadToString(axis2MessageContext);
        } else {
            payload = "{}";
        }

        // Put the regex here as required to replace/remove a particular word/phrase in the payload with the given replacement
        String modifiedPayload = payload.replaceAll("\\s*Delete\\b\\s*","");

        JsonUtil.newJsonPayload(axis2MessageContext, modifiedPayload, true, true);

        return true;
    }
}
