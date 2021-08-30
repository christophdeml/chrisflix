document.addEventListener("DOMContentLoaded", () => {
    document.querySelector('#btn-save-useragent-name').addEventListener('click', () => {
        document.cookie = "USERAGENTNAME=" + document.querySelector('#useragent-name').value.replaceAll(' ', '%20');
        fetch('http://' + document.location.host + '/setname?name=' + document.querySelector('#useragent-name').value).then();
    });
});