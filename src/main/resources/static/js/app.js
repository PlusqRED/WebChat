var stompClient = null;

connect();

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body));
        });
        stompClient.subscribe("/topic/url", function (loadedData) {
            showLoadedData(JSON.parse(loadedData.body))
        });
    });
}

function showLoadedData(loadedData) {
    $("#loadedData").append(loadedData.data);
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendMessage() {
    var date = new Date($.now());
    stompClient.send("/app/message", {}, JSON.stringify({
        'name': $("#name").val(),
        'content': $("#messageContent").val(),
        'hours': date.getHours(),
        'minutes': date.getMinutes(),
        'seconds': date.getSeconds()
    }));
}

function sendUrl() {
    stompClient.send("/app/url", {}, JSON.stringify({
        'url': $("#url").val()
    }))
}

function showMessage(message) {
    $("#conversation").append("<tr><td>" + message.countNumber
        + " " + message.name + " "
        + message.content + " "
        + message.hours + ":" + message.minutes + ":" + message.seconds
        + "</td></tr>");
}

//binding buttons
$(function () {
    $("#sendButton").click(function () {
        sendMessage();
    });
    $("#loadText").click(function () {
        sendUrl();
    })
});