<!DOCTYPE html>
<html lang="en">
<head>
    <title>WebSocket Chat</title>
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/js/app.js"></script>
</head>
<body>
<noscript>
    <h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
        enabled. Please enable
        Javascript and reload this page!</h2>
</noscript>
<div>
    <h2>${helloMessage}</h2>
    <label for="name" class="label label-default">Name: </label>
    <input type="text" id="name"/>
    <label for="messageContent" class="label label-default">Message:</label>
    <input type="text" id="messageContent">
    <button id="sendButton" class="btn btn-default">Send</button>
    <label for="url">Url: </label>
    <input type="text" id="url">
    <button id="loadText" class="btn btn-default">Load from url</button>
</div>
<table class="table-hover table-striped" id="conversation"></table>
<div id="loadedData"></div>
</body>
</html>