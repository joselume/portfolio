function getPortfolioEndpoint(id, readyOnly) {
      const response = fetch('portfolio/' + id).then(function(response) {
          if (response.ok) {
            response.json().then(function(jsonResponse) {
                readyOnly ? populatePortfolioData(jsonResponse) : populatePortfolioUpdate(jsonResponse);
            })
          } else  {
            response.json().then(function(jsonResponse) {
                alert(" Message: " + jsonResponse.message + " \nStatus: " +  jsonResponse.status);
                location.href = "http://localhost:8082/index.html";
            })
          }
       })
}

function updatePortfolioData() {

    var portfolio = new Object();

    portfolio.twitterUser = document.getElementById('username').value;
    portfolio.title = document.getElementById('title').value;
    portfolio.description = document.getElementById('description').value;
    portfolio.image = document.getElementById("image").value;
    portfolio.id = document.getElementById("portfolioid").value;

    const portfolioJson = JSON.stringify(portfolio);

    const response = fetch('portfolio/' + portfolio.id, {
        method: 'PUT',
        body: portfolioJson,
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json, text/plain, */*'
        }
      }).then(function(response) {
          if (response.ok) {
            response.json().then(function(jsonResponse) {
                location.href = "portfolio.html?id=" + portfolio.id;
            })
          } else  {
            response.json().then(function(jsonResponse) {
                alert(" Message: " + jsonResponse.message + " \nStatus: " +  jsonResponse.status);
                // location.href = "http://localhost:8082/index.html";
            })
          }
    });
}


function populatePortfolioData(portfolioJson) {

    document.getElementById('title').innerHTML =  portfolioJson.portfolio.title;
    document.getElementById('description').innerHTML =  portfolioJson.portfolio.description;
    document.getElementById('username').innerHTML =  portfolioJson.portfolio.twitterUser;
    document.getElementById("image").src= portfolioJson.portfolio.image;

    if (portfolioJson.tweets.length > 0) {
      var tweetsHtml = '';
      portfolioJson.tweets.forEach(element => {
        var fromUser = element.retweetedStatus && element.retweetedStatus.fromUser ? element.retweetedStatus.fromUser :  element.fromUser;
        var profileImageUrl = element.retweetedStatus && element.retweetedStatus.profileImageUrl ? element.retweetedStatus.profileImageUrl :  element.profileImageUrl;
        tweetsHtml +=
            "<img src='" + profileImageUrl + "' > " +
            "<b>"+ fromUser +":</b><br> " +
            element.text + "<br><br>"
      });
      document.getElementById('tweets').innerHTML =  tweetsHtml;
    } else {
       document.getElementById('tweets').innerHTML =  "";
    }
}

function populatePortfolioUpdate(portfolioJson) {
    document.getElementById('username').value =  portfolioJson.portfolio.twitterUser;
    document.getElementById('title').value =  portfolioJson.portfolio.title;
    document.getElementById('description').value =  portfolioJson.portfolio.description;
    document.getElementById("image").value = portfolioJson.portfolio.image;
    document.getElementById("portfolioid").value = portfolioJson.portfolio.id;
}

function getProfileId() {
    const parameters = new URLSearchParams(window.location.search);
    return parameters.get('id');
}

function renderPortfolioData(readyOnly) {
    const id = getProfileId();
    getPortfolioEndpoint(id, readyOnly);
}