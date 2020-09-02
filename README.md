# custom-payload-transfer-mediator
This custom class mediator modifies a request/response payload. You can modify the regex here to replace/remove any word/phrase in the payload.

## Instructions
1. Clone the project and go to the PayloadTransferMediator.java class.
2. Modify the replaceAll method's regex as required. Here I am replacing "Delete" word with "" in the replaceAll method. You can use whatever a regex to replace/remove a word/phrase in your payload.
3. Modify the pom file's synapse-core dependency version to match with your APIM version.
4. Build the project using "mvn clean install"
5. Copy the custom.payloadtransfer.mediator-1.0.jar inside target directory to the <APIM_HOME>/repository/components/lib directory.
6. Restart the server.
7. To use the class mediator in an API to modify the request/response payload, use the following sample-sequence.xml file as the in/out sequence in the API.

```
<?xml version="1.0" encoding="UTF-8"?>
<sequence
	xmlns="http://ws.apache.org/ns/synapse"
		  name="admin--Test--In">
	<log>
    		<property name="JSON-PAYLOAD_BEFORE_MODIFYING" expression="json-eval($.)"/>
	</log>
	<class name="com.custom.PayloadTransferMediator" />
	<log>
    		<property name="JSON-PAYLOAD_AFTER_MODIFYING" expression="json-eval($.)"/>
	</log>
</sequence>
```

8. When you invoke the API, you will see the logs getting printed in the Console with the modified payload.
