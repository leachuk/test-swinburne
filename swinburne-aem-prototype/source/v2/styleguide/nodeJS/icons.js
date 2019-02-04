let fs = require('fs');

let template = ``;
let icons = [
  { name: "home", class: "Home" },
  { name: "chevron-right", class: "Chevron Right" },
  { name: "calculator", class: "Calculator" },
  { name: "briefcase", class: "Briefcase" },
  { name: "fast-forward", class: "Fast Forward" },
  { name: "comments", class: "Comments" },
  { name: "search", class: "Search" },
  { name: "calendar", class: "Calendar" },
  { name: "calendar-alt", class: "Calendar Alt" },
  { name: "edit", class: "Edit" },
  { name: "phone", class: "Phone" },
  { name: "pencil-alt", class: "Pencil Alt" },
  { name: "book", class: "Book" },
  { name: "reply-all", class: "Reply All" },
  { name: "life-ring", class: "Life Ring" },
  { name: "envelope", class: "Envelope" },
  { name: "star", class: "Star" },
  { name: "car", class: "Car" },
  { name: "street-view", class: "Street View" },
  { name: "globe", class: "Globe" },
  { name: "train", class: "Train" },
  { name: "play-circle", class: "Play Circle" },
  { name: "user", class: "User" },
  { name: "users", class: "Users" },
  { name: "users-class", class: "Users Class" },
  { name: "user-graduate", class: "User Graduate" },
  { name: "balance-scale", class: "Balance Scale" },
  { name: "building", class: "Building" },
  { name: "chalkboard-teacher", class: "Chalkboard Teacher" },
  { name: "child", class: "Child" },
  { name: "comment-alt-exclamation", class: "Comment Al Exclamation" },
  { name: "comment-alt-smile", class: "Comment Alt Smile" },
  { name: "comment-alt-check", class: "Comment Alt Check" },
  { name: "comment-alt-dots", class: "Comment Alt Dots" },
  { name: "comments-alt", class: "Comment Alt" },
  { name: "info-circle", class: "Info Circle" },
  { name: "laptop", class: "Laptop" },
  { name: "location-circle", class: "Location Circle" },
  { name: "map-pin", class: "Map pin" },
  { name: "plane-arrival", class: "Plane Arrival" },
  { name: "plane-departure", class: "Plane Departure" },
  { name: "thumbs-up", class: "Thumbs Up" },
  { name: "thumbs-down", class: "Thumbs Down" },
  { name: "trophy", class: "Trophy" },
  { name: "wheelchair", class: "Wheelchair" },
  { name: "star", class: "Star" },
  { name: "school", class: "School" },
  { name: "paper-plane", class: "Paper Plane" },
  { name: "newspaper", class: "Newspaper" },
  { name: "bar-chart", class: "Bar-Chart" },
  { name: "university", class: "University" },
  { name: "video", class: "Video" },
  { name: "graduation-cap", class: "Graduation Cap" },
  { name: "cogs", class: "Cogs" },
  { name: "gamepad", class: "Gamepad" },
  { name: "code", class: "Code" },
  { name: "gavel", class: "Gavel" },
  { name: "stethoscope", class: "Stethoscope" },
  { name: "flask", class: "Flask" },
  { name: "wrench", class: "Wrench" },
  { name: "heartbeat", class: "Heartbeat" },
  { name: "link", class: "Link" },
  { name: "connectdevelop", class: "Connectdevelop" },
  { name: "facebook-f", class: "Facebook F" },
  { name: "facebook", class: "Facebook" },
  { name: "instagram", class: "Instagram" },
  { name: "twitter", class: "Twitter" },
  { name: "pinterest-p", class: "Pinterest P" },
  { name: "linkedin-in", class: "Linkedin In" },
  { name: "linkedin", class: "Linkedin" },
  { name: "youtube", class: "Youtube" },
  { name: "tumblr-square", class: "Tumblr Square" },
  { name: "tumblr", class: "Tumblr" }
];

let row_index = 0;
icons.forEach( (icon, index) => {
  if(row_index === 0) template += `<div id="icon-section-${index}" class="row">`;
  template += `<div class="col-xl-3 col-lg-4 col-sm-6 icon"><span class="fa fa-${icon.name}"></span><span>${icon.class}</span></div>`;
  row_index++;
  if(row_index === 12 || !icons[index + 1]) {
    template += `</div>`;
    row_index = 0;
  }
});


fs.writeFile ("../html/icons.html", template, function(err) {
    if (err) throw err;
    console.log('complete');
  }
);
