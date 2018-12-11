let fs = require('fs');

let template = `<div class="row">`;
const breakpoints = ["" , "-sm", "-md", "-lg", "-xl"];
const grids = [
  {value :3, repeat: 4, bg: "bg-c1"},
  {value :4, repeat: 3, bg: "bg-c2"},
  {value :6, repeat: 2, bg: "bg-c4"},
  {value :2, repeat: 6, bg: "bg-c5"},
  {value :1, repeat: 12, bg: "bg-c6"}
];

grids.map((grid) => {
  template += `<div class="col-12"><h4>${grid.repeat} columns grid</h4></div>`;
  breakpoints.map((breakpoint) => {
    let columns = new Array(grid.repeat);
    columns.fill("").map(() => {
      template += `<div class="col${breakpoint}-${grid.value}"><div class="wrapper ${grid.bg}" style="min-height:60px;"><span>col${breakpoint}-${grid.value}</span></div></div>`;
    });
  });
});

template += `</div>`;


fs.writeFile ("../html/grid.html", template, function(err) {
    if (err) throw err;
    console.log('complete');
  }
);
