USE CASE : Event handler just prints the event properties in the console (Username & tenant-domain). 
But you can customize the event handler to do whatever you want (Ex: Send an email after adding a user).

CONFIGURATION : 
[[event_handler]]
name="customEventHandler"
subscriptions=["PRE_ADD_USER","POST_ADD_USER"]

The following guide shows you how to write a custom event handler.
https://is.docs.wso2.com/en/6.0.0/references/extend/user-mgt/write-a-custom-event-handler/#write-an-event-handler
