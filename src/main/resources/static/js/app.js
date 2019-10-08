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
    });
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

function showMessage(message) {
    $("#conversation").append("<tr><td>" + message.countNumber
        + " " + message.name + " "
        + message.content + " "
        + message.hours + ":" + message.minutes + ":" + message.seconds
        + "</td></tr>");
}

$(function () {
    $("#sendButton").click(function () {
        sendMessage();
    });
});