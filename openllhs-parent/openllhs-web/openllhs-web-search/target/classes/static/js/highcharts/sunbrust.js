/*
 Highcharts JS v8.0.4 (2020-03-10)

 (c) 2016-2019 Highsoft AS
 Authors: Jon Arild Nygard

 License: www.highcharts.com/license
*/
(function(d){"object"===typeof module&&module.exports?(d["default"]=d,module.exports=d):"function"===typeof define&&define.amd?define("highcharts/modules/sunburst",["highcharts"],function(m){d(m);d.Highcharts=m;return d}):d("undefined"!==typeof Highcharts?Highcharts:void 0)})(function(d){function m(d,b,K,n){d.hasOwnProperty(b)||(d[b]=n.apply(null,K))}d=d?d._modules:{};m(d,"mixins/draw-point.js",[],function(){var d=function(b){var d=this,n=d.graphic,w=b.animatableAttribs,q=b.onComplete,g=b.css,l=b.renderer;
    if(d.shouldDraw())n||(d.graphic=n=l[b.shapeType](b.shapeArgs).add(b.group)),n.css(g).attr(b.attribs).animate(w,b.isNew?!1:void 0,q);else if(n){var y=function(){d.graphic=n=n.destroy();"function"===typeof q&&q()};Object.keys(w).length?n.animate(w,void 0,function(){y()}):y()}};return function(b){(b.attribs=b.attribs||{})["class"]=this.getClassName();d.call(this,b)}});m(d,"mixins/tree-series.js",[d["parts/Color.js"],d["parts/Utilities.js"]],function(d,b){var w=b.extend,n=b.isArray,m=b.isNumber,q=b.isObject,
    g=b.merge,l=b.pick;return{getColor:function(b,p){var r=p.index,y=p.mapOptionsToLevel,g=p.parentColor,w=p.parentColorIndex,n=p.series,z=p.colors,q=p.siblings,t=n.points,B=n.chart.options.chart,D;if(b){t=t[b.i];b=y[b.level]||{};if(y=t&&b.colorByPoint){var v=t.index%(z?z.length:B.colorCount);var m=z&&z[v]}if(!n.chart.styledMode){z=t&&t.options.color;B=b&&b.color;if(D=g)D=(D=b&&b.colorVariation)&&"brightness"===D.key?d.parse(g).brighten(r/q*D.to).get():g;D=l(z,B,m,D,n.color)}var E=l(t&&t.options.colorIndex,
        b&&b.colorIndex,v,w,p.colorIndex)}return{color:D,colorIndex:E}},getLevelOptions:function(b){var d=null;if(q(b)){d={};var r=m(b.from)?b.from:1;var l=b.levels;var y={};var G=q(b.defaults)?b.defaults:{};n(l)&&(y=l.reduce(function(b,d){if(q(d)&&m(d.level)){var p=g({},d);var n="boolean"===typeof p.levelIsConstant?p.levelIsConstant:G.levelIsConstant;delete p.levelIsConstant;delete p.level;d=d.level+(n?0:r-1);q(b[d])?w(b[d],p):b[d]=p}return b},{}));l=m(b.to)?b.to:1;for(b=0;b<=l;b++)d[b]=g({},G,q(y[b])?y[b]:
        {})}return d},setTreeValues:function T(b,d){var g=d.before,n=d.idRoot,p=d.mapIdToNode[n],r=d.points[b.i],q=r&&r.options||{},t=0,B=[];w(b,{levelDynamic:b.level-(("boolean"===typeof d.levelIsConstant?d.levelIsConstant:1)?0:p.level),name:l(r&&r.name,""),visible:n===b.id||("boolean"===typeof d.visible?d.visible:!1)});"function"===typeof g&&(b=g(b,d));b.children.forEach(function(g,n){var p=w({},d);w(p,{index:n,siblings:b.children.length,visible:b.visible});g=T(g,p);B.push(g);g.visible&&(t+=g.val)});b.visible=
        0<t||b.visible;g=l(q.value,t);w(b,{children:B,childrenTotal:t,isLeaf:b.visible&&!t,val:g});return b},updateRootId:function(b){if(q(b)){var d=q(b.options)?b.options:{};d=l(b.rootNode,d.rootId,"");q(b.userOptions)&&(b.userOptions.rootId=d);b.rootNode=d}return d}}});m(d,"modules/treemap.src.js",[d["parts/Globals.js"],d["mixins/tree-series.js"],d["mixins/draw-point.js"],d["parts/Color.js"],d["mixins/legend-symbol.js"],d["parts/Point.js"],d["parts/Utilities.js"]],function(d,b,m,n,S,q,g){var l=n.parse,
    y=g.addEvent,p=g.correctFloat,r=g.defined,w=g.error,J=g.extend,G=g.fireEvent,L=g.isArray,z=g.isNumber,K=g.isObject,t=g.isString,B=g.merge,D=g.objectEach,v=g.pick;n=g.seriesType;var P=g.stableSort,E=d.seriesTypes;g=d.noop;var M=b.getColor,N=b.getLevelOptions,F=d.Series,f=function(a,c,e){e=e||this;D(a,function(A,h){c.call(e,A,h,a)})},k=function(a,c,e){e=e||this;a=c.call(e,a);!1!==a&&k(a,c,e)},u=b.updateRootId,C=!1;n("treemap","scatter",{allowTraversingTree:!1,animationLimit:250,showInLegend:!1,marker:!1,
    colorByPoint:!1,dataLabels:{defer:!1,enabled:!0,formatter:function(){var a=this&&this.point?this.point:{};return t(a.name)?a.name:""},inside:!0,verticalAlign:"middle"},tooltip:{headerFormat:"",pointFormat:"<b>{point.name}</b>: {point.value}<br/>"},ignoreHiddenPoint:!0,layoutAlgorithm:"sliceAndDice",layoutStartingDirection:"vertical",alternateStartingDirection:!1,levelIsConstant:!0,drillUpButton:{position:{align:"right",x:-10,y:10}},traverseUpButton:{position:{align:"right",x:-10,y:10}},borderColor:"#e6e6e6",
    borderWidth:1,colorKey:"colorValue",opacity:.15,states:{hover:{borderColor:"#999999",brightness:E.heatmap?0:.1,halo:!1,opacity:.75,shadow:!1}}},{pointArrayMap:["value"],directTouch:!0,optionalAxis:"colorAxis",getSymbol:g,parallelArrays:["x","y","value","colorValue"],colorKey:"colorValue",trackerGroups:["group","dataLabelsGroup"],getListOfParents:function(a,c){a=L(a)?a:[];var e=L(c)?c:[];c=a.reduce(function(a,c,e){c=v(c.parent,"");"undefined"===typeof a[c]&&(a[c]=[]);a[c].push(e);return a},{"":[]});
        f(c,function(a,c,b){""!==c&&-1===e.indexOf(c)&&(a.forEach(function(a){b[""].push(a)}),delete b[c])});return c},getTree:function(){var a=this.data.map(function(a){return a.id});a=this.getListOfParents(this.data,a);this.nodeMap=[];return this.buildNode("",-1,0,a,null)},hasData:function(){return!!this.processedXData.length},init:function(a,c){var e=d.colorMapSeriesMixin;e&&(this.colorAttribs=e.colorAttribs);this.eventsToUnbind.push(y(this,"setOptions",function(a){a=a.userOptions;r(a.allowDrillToNode)&&
    !r(a.allowTraversingTree)&&(a.allowTraversingTree=a.allowDrillToNode,delete a.allowDrillToNode);r(a.drillUpButton)&&!r(a.traverseUpButton)&&(a.traverseUpButton=a.drillUpButton,delete a.drillUpButton)}));F.prototype.init.call(this,a,c);this.options.allowTraversingTree&&this.eventsToUnbind.push(y(this,"click",this.onClickDrillToNode))},buildNode:function(a,c,e,b,h){var f=this,d=[],A=f.points[c],k=0,H;(b[a]||[]).forEach(function(c){H=f.buildNode(f.points[c].id,c,e+1,b,a);k=Math.max(H.height+1,k);d.push(H)});
        c={id:a,i:c,children:d,height:k,level:e,parent:h,visible:!1};f.nodeMap[c.id]=c;A&&(A.node=c);return c},setTreeValues:function(a){var c=this,e=c.options,b=c.nodeMap[c.rootNode];e="boolean"===typeof e.levelIsConstant?e.levelIsConstant:!0;var h=0,f=[],d=c.points[a.i];a.children.forEach(function(a){a=c.setTreeValues(a);f.push(a);a.ignore||(h+=a.val)});P(f,function(a,c){return a.sortIndex-c.sortIndex});var k=v(d&&d.options.value,h);d&&(d.value=k);J(a,{children:f,childrenTotal:h,ignore:!(v(d&&d.visible,
            !0)&&0<k),isLeaf:a.visible&&!h,levelDynamic:a.level-(e?0:b.level),name:v(d&&d.name,""),sortIndex:v(d&&d.sortIndex,-k),val:k});return a},calculateChildrenAreas:function(a,c){var e=this,b=e.options,h=e.mapOptionsToLevel[a.level+1],f=v(e[h&&h.layoutAlgorithm]&&h.layoutAlgorithm,b.layoutAlgorithm),d=b.alternateStartingDirection,k=[];a=a.children.filter(function(a){return!a.ignore});h&&h.layoutStartingDirection&&(c.direction="vertical"===h.layoutStartingDirection?0:1);k=e[f](c,a);a.forEach(function(a,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               h){h=k[h];a.values=B(h,{val:a.childrenTotal,direction:d?1-c.direction:c.direction});a.pointValues=B(h,{x:h.x/e.axisRatio,y:100-h.y-h.height,width:h.width/e.axisRatio});a.children.length&&e.calculateChildrenAreas(a,a.values)})},setPointValues:function(){var a=this,c=a.xAxis,e=a.yAxis,b=a.chart.styledMode;a.points.forEach(function(h){var f=h.node,d=f.pointValues;f=f.visible;if(d&&f){f=d.height;var k=d.width,A=d.x,u=d.y,g=b?0:(a.pointAttribs(h)["stroke-width"]||0)%2/2;d=Math.round(c.toPixels(A,!0))-
        g;k=Math.round(c.toPixels(A+k,!0))-g;A=Math.round(e.toPixels(u,!0))-g;f=Math.round(e.toPixels(u+f,!0))-g;h.shapeArgs={x:Math.min(d,k),y:Math.min(A,f),width:Math.abs(k-d),height:Math.abs(f-A)};h.plotX=h.shapeArgs.x+h.shapeArgs.width/2;h.plotY=h.shapeArgs.y+h.shapeArgs.height/2}else delete h.plotX,delete h.plotY})},setColorRecursive:function(a,c,e,f,h){var b=this,d=b&&b.chart;d=d&&d.options&&d.options.colors;if(a){var k=M(a,{colors:d,index:f,mapOptionsToLevel:b.mapOptionsToLevel,parentColor:c,parentColorIndex:e,
        series:b,siblings:h});if(c=b.points[a.i])c.color=k.color,c.colorIndex=k.colorIndex;(a.children||[]).forEach(function(c,e){b.setColorRecursive(c,k.color,k.colorIndex,e,a.children.length)})}},algorithmGroup:function(a,c,e,f){this.height=a;this.width=c;this.plot=f;this.startDirection=this.direction=e;this.lH=this.nH=this.lW=this.nW=this.total=0;this.elArr=[];this.lP={total:0,lH:0,nH:0,lW:0,nW:0,nR:0,lR:0,aspectRatio:function(a,c){return Math.max(a/c,c/a)}};this.addElement=function(a){this.lP.total=this.elArr[this.elArr.length-
    1];this.total+=a;0===this.direction?(this.lW=this.nW,this.lP.lH=this.lP.total/this.lW,this.lP.lR=this.lP.aspectRatio(this.lW,this.lP.lH),this.nW=this.total/this.height,this.lP.nH=this.lP.total/this.nW,this.lP.nR=this.lP.aspectRatio(this.nW,this.lP.nH)):(this.lH=this.nH,this.lP.lW=this.lP.total/this.lH,this.lP.lR=this.lP.aspectRatio(this.lP.lW,this.lH),this.nH=this.total/this.width,this.lP.nW=this.lP.total/this.nH,this.lP.nR=this.lP.aspectRatio(this.lP.nW,this.nH));this.elArr.push(a)};this.reset=function(){this.lW=
        this.nW=0;this.elArr=[];this.total=0}},algorithmCalcPoints:function(a,c,e,f){var h,b,d,k,A=e.lW,u=e.lH,g=e.plot,C=0,n=e.elArr.length-1;if(c)A=e.nW,u=e.nH;else var l=e.elArr[e.elArr.length-1];e.elArr.forEach(function(a){if(c||C<n)0===e.direction?(h=g.x,b=g.y,d=A,k=a/d):(h=g.x,b=g.y,k=u,d=a/k),f.push({x:h,y:b,width:d,height:p(k)}),0===e.direction?g.y+=k:g.x+=d;C+=1});e.reset();0===e.direction?e.width-=A:e.height-=u;g.y=g.parent.y+(g.parent.height-e.height);g.x=g.parent.x+(g.parent.width-e.width);a&&
    (e.direction=1-e.direction);c||e.addElement(l)},algorithmLowAspectRatio:function(a,c,e){var f=[],h=this,b,d={x:c.x,y:c.y,parent:c},k=0,g=e.length-1,u=new this.algorithmGroup(c.height,c.width,c.direction,d);e.forEach(function(e){b=e.val/c.val*c.height*c.width;u.addElement(b);u.lP.nR>u.lP.lR&&h.algorithmCalcPoints(a,!1,u,f,d);k===g&&h.algorithmCalcPoints(a,!0,u,f,d);k+=1});return f},algorithmFill:function(a,c,e){var f=[],h,b=c.direction,d=c.x,k=c.y,g=c.width,u=c.height,C,n,l,p;e.forEach(function(e){h=
        e.val/c.val*c.height*c.width;C=d;n=k;0===b?(p=u,l=h/p,g-=l,d+=l):(l=g,p=h/l,u-=p,k+=p);f.push({x:C,y:n,width:l,height:p});a&&(b=1-b)});return f},strip:function(a,c){return this.algorithmLowAspectRatio(!1,a,c)},squarified:function(a,c){return this.algorithmLowAspectRatio(!0,a,c)},sliceAndDice:function(a,c){return this.algorithmFill(!0,a,c)},stripes:function(a,c){return this.algorithmFill(!1,a,c)},translate:function(){var a=this,c=a.options,e=u(a);F.prototype.translate.call(a);var f=a.tree=a.getTree();
        var b=a.nodeMap[e];a.renderTraverseUpButton(e);a.mapOptionsToLevel=N({from:b.level+1,levels:c.levels,to:f.height,defaults:{levelIsConstant:a.options.levelIsConstant,colorByPoint:c.colorByPoint}});""===e||b&&b.children.length||(a.setRootNode("",!1),e=a.rootNode,b=a.nodeMap[e]);k(a.nodeMap[a.rootNode],function(c){var e=!1,b=c.parent;c.visible=!0;if(b||""===b)e=a.nodeMap[b];return e});k(a.nodeMap[a.rootNode].children,function(a){var c=!1;a.forEach(function(a){a.visible=!0;a.children.length&&(c=(c||[]).concat(a.children))});
            return c});a.setTreeValues(f);a.axisRatio=a.xAxis.len/a.yAxis.len;a.nodeMap[""].pointValues=e={x:0,y:0,width:100,height:100};a.nodeMap[""].values=e=B(e,{width:e.width*a.axisRatio,direction:"vertical"===c.layoutStartingDirection?0:1,val:f.val});a.calculateChildrenAreas(f,e);a.colorAxis||c.colorByPoint||a.setColorRecursive(a.tree);c.allowTraversingTree&&(c=b.pointValues,a.xAxis.setExtremes(c.x,c.x+c.width,!1),a.yAxis.setExtremes(c.y,c.y+c.height,!1),a.xAxis.setScale(),a.yAxis.setScale());a.setPointValues()},
    drawDataLabels:function(){var a=this,c=a.mapOptionsToLevel,e,b;a.points.filter(function(a){return a.node.visible}).forEach(function(f){b=c[f.node.level];e={style:{}};f.node.isLeaf||(e.enabled=!1);b&&b.dataLabels&&(e=B(e,b.dataLabels),a._hasPointLabels=!0);f.shapeArgs&&(e.style.width=f.shapeArgs.width,f.dataLabel&&f.dataLabel.css({width:f.shapeArgs.width+"px"}));f.dlOptions=B(e,f.options.dataLabels)});F.prototype.drawDataLabels.call(this)},alignDataLabel:function(a,c,e){var f=e.style;!r(f.textOverflow)&&
    c.text&&c.getBBox().width>c.text.textWidth&&c.css({textOverflow:"ellipsis",width:f.width+="px"});E.column.prototype.alignDataLabel.apply(this,arguments);a.dataLabel&&a.dataLabel.attr({zIndex:(a.node.zIndex||0)+1})},pointAttribs:function(a,c){var e=K(this.mapOptionsToLevel)?this.mapOptionsToLevel:{},f=a&&e[a.node.level]||{};e=this.options;var b=c&&e.states[c]||{},d=a&&a.getClassName()||"";a={stroke:a&&a.borderColor||f.borderColor||b.borderColor||e.borderColor,"stroke-width":v(a&&a.borderWidth,f.borderWidth,
            b.borderWidth,e.borderWidth),dashstyle:a&&a.borderDashStyle||f.borderDashStyle||b.borderDashStyle||e.borderDashStyle,fill:a&&a.color||this.color};-1!==d.indexOf("highcharts-above-level")?(a.fill="none",a["stroke-width"]=0):-1!==d.indexOf("highcharts-internal-node-interactive")?(c=v(b.opacity,e.opacity),a.fill=l(a.fill).setOpacity(c).get(),a.cursor="pointer"):-1!==d.indexOf("highcharts-internal-node")?a.fill="none":c&&(a.fill=l(a.fill).brighten(b.brightness).get());return a},drawPoints:function(){var a=
        this,c=a.chart,e=c.renderer,f=c.styledMode,b=a.options,d=f?{}:b.shadow,k=b.borderRadius,g=c.pointCount<b.animationLimit,u=b.allowTraversingTree;a.points.forEach(function(c){var h=c.node.levelDynamic,A={},C={},n={},l="level-group-"+h,Q=!!c.graphic,p=g&&Q,R=c.shapeArgs;c.shouldDraw()&&(k&&(C.r=k),B(!0,p?A:C,Q?R:{},f?{}:a.pointAttribs(c,c.selected&&"select")),a.colorAttribs&&f&&J(n,a.colorAttribs(c)),a[l]||(a[l]=e.g(l).attr({zIndex:1E3-h}).add(a.group),a[l].survive=!0));c.draw({animatableAttribs:A,attribs:C,
        css:n,group:a[l],renderer:e,shadow:d,shapeArgs:R,shapeType:"rect"});u&&c.graphic&&(c.drillId=b.interactByLeaf?a.drillToByLeaf(c):a.drillToByGroup(c))})},onClickDrillToNode:function(a){var c=(a=a.point)&&a.drillId;t(c)&&(a.setState(""),this.setRootNode(c,!0,{trigger:"click"}))},drillToByGroup:function(a){var c=!1;1!==a.node.level-this.nodeMap[this.rootNode].level||a.node.isLeaf||(c=a.id);return c},drillToByLeaf:function(a){var c=!1;if(a.node.parent!==this.rootNode&&a.node.isLeaf)for(a=a.node;!c;)a=
        this.nodeMap[a.parent],a.parent===this.rootNode&&(c=a.id);return c},drillUp:function(){var a=this.nodeMap[this.rootNode];a&&t(a.parent)&&this.setRootNode(a.parent,!0,{trigger:"traverseUpButton"})},drillToNode:function(a,c){w("WARNING: treemap.drillToNode has been renamed to treemap.setRootNode, and will be removed in the next major version.");this.setRootNode(a,c)},setRootNode:function(a,c,e){a=J({newRootId:a,previousRootId:this.rootNode,redraw:v(c,!0),series:this},e);G(this,"setRootNode",a,function(a){var c=
        a.series;c.idPreviousRoot=a.previousRootId;c.rootNode=a.newRootId;c.isDirty=!0;a.redraw&&c.chart.redraw()})},renderTraverseUpButton:function(a){var c=this,e=c.options.traverseUpButton,f=v(e.text,c.nodeMap[a].name,"< Back");if(""===a)c.drillUpButton&&(c.drillUpButton=c.drillUpButton.destroy());else if(this.drillUpButton)this.drillUpButton.placed=!1,this.drillUpButton.attr({text:f}).align();else{var b=(a=e.theme)&&a.states;this.drillUpButton=this.chart.renderer.button(f,null,null,function(){c.drillUp()},
        a,b&&b.hover,b&&b.select).addClass("highcharts-drillup-button").attr({align:e.position.align,zIndex:7}).add().align(e.position,!1,e.relativeTo||"plotBox")}},buildKDTree:g,drawLegendSymbol:S.drawRectangle,getExtremes:function(){F.prototype.getExtremes.call(this,this.colorValueData);this.valueMin=this.dataMin;this.valueMax=this.dataMax;F.prototype.getExtremes.call(this)},getExtremesFromAll:!0,setState:function(a){this.options.inactiveOtherPoints=!0;F.prototype.setState.call(this,a,!1);this.options.inactiveOtherPoints=
        !1},utils:{recursive:k}},{draw:m,setVisible:E.pie.prototype.pointClass.prototype.setVisible,getClassName:function(){var a=q.prototype.getClassName.call(this),c=this.series,b=c.options;this.node.level<=c.nodeMap[c.rootNode].level?a+=" highcharts-above-level":this.node.isLeaf||v(b.interactByLeaf,!b.allowTraversingTree)?this.node.isLeaf||(a+=" highcharts-internal-node"):a+=" highcharts-internal-node-interactive";return a},isValid:function(){return this.id||z(this.value)},setState:function(a){q.prototype.setState.call(this,
        a);this.graphic&&this.graphic.attr({zIndex:"hover"===a?1:0})},shouldDraw:function(){return z(this.plotY)&&null!==this.y}});y(d.Series,"afterBindAxes",function(){var a=this.xAxis,c=this.yAxis;if(a&&c)if(this.is("treemap")){var b={endOnTick:!1,gridLineWidth:0,lineWidth:0,min:0,dataMin:0,minPadding:0,max:100,dataMax:100,maxPadding:0,startOnTick:!1,title:null,tickPositions:[]};J(c.options,b);J(a.options,b);C=!0}else C&&(c.setOptions(c.userOptions),a.setOptions(a.userOptions),C=!1)});""});m(d,"modules/sunburst.src.js",
    [d["parts/Globals.js"],d["parts/Utilities.js"],d["mixins/draw-point.js"],d["mixins/tree-series.js"]],function(d,b,m,n){var w=b.correctFloat,q=b.error,g=b.extend,l=b.isNumber,y=b.isObject,p=b.isString,r=b.merge,K=b.seriesType,J=b.splat;b=d.CenteredSeriesMixin;var G=d.Series,L=b.getCenter,z=n.getColor,U=n.getLevelOptions,t=b.getStartAndEndRadians,B=180/Math.PI,D=d.seriesTypes,v=n.setTreeValues,P=n.updateRootId,E=function(b,d){var f=[];if(l(b)&&l(d)&&b<=d)for(;b<=d;b++)f.push(b);return f},M=function(b,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          d){d=y(d)?d:{};var f=0,k;if(y(b)){var a=r({},b);b=l(d.from)?d.from:0;var c=l(d.to)?d.to:0;var e=E(b,c);b=Object.keys(a).filter(function(a){return-1===e.indexOf(+a)});var g=k=l(d.diffRadius)?d.diffRadius:0;e.forEach(function(c){c=a[c];var b=c.levelSize.unit,e=c.levelSize.value;"weight"===b?f+=e:"percentage"===b?(c.levelSize={unit:"pixels",value:e/100*g},k-=c.levelSize.value):"pixels"===b&&(k-=e)});e.forEach(function(c){var b=a[c];"weight"===b.levelSize.unit&&(b=b.levelSize.value,a[c].levelSize={unit:"pixels",
        value:b/f*k})});b.forEach(function(c){a[c].levelSize={value:0,unit:"pixels"}})}return a},N=function(b){var f=b.level;return{from:0<f?f:1,to:f+b.height}},F=function(b,d){var f=d.mapIdToNode[b.parent],k=d.series,a=k.chart,c=k.points[b.i];f=z(b,{colors:k.options.colors||a&&a.options.colors,colorIndex:k.colorIndex,index:d.index,mapOptionsToLevel:d.mapOptionsToLevel,parentColor:f&&f.color,parentColorIndex:f&&f.colorIndex,series:d.series,siblings:d.siblings});b.color=f.color;b.colorIndex=f.colorIndex;c&&
    (c.color=b.color,c.colorIndex=b.colorIndex,b.sliced=b.id!==d.idRoot?c.sliced:!1);return b};K("sunburst","treemap",{center:["50%","50%"],colorByPoint:!1,opacity:1,dataLabels:{allowOverlap:!0,defer:!0,rotationMode:"auto",style:{textOverflow:"ellipsis"}},rootId:void 0,levelIsConstant:!0,levelSize:{value:1,unit:"weight"},slicedOffset:10},{drawDataLabels:d.noop,drawPoints:function(){var b=this,d=b.mapOptionsToLevel,u=b.shapeRoot,n=b.group,a=b.hasRendered,c=b.rootNode,e=b.idPreviousRoot,A=b.nodeMap,h=A[e],
            p=h&&h.shapeArgs;h=b.points;var q=b.startAndEndRadians,t=b.chart,m=t&&t.options&&t.options.chart||{},w="boolean"===typeof m.animation?m.animation:!0,v=b.center[3]/2,D=b.chart.renderer,z=!1,E=!1;if(m=!!(w&&a&&c!==e&&b.dataLabelsGroup)){b.dataLabelsGroup.attr({opacity:0});var F=function(){z=!0;b.dataLabelsGroup&&b.dataLabelsGroup.animate({opacity:1,visibility:"visible"})}}h.forEach(function(f){var k=f.node,h=d[k.level];var C=f.shapeExisting||{};var m=k.shapeArgs||{},z=!(!k.visible||!k.shapeArgs);if(a&&
            w){var H={};var O={end:m.end,start:m.start,innerR:m.innerR,r:m.r,x:m.x,y:m.y};z?!f.graphic&&p&&(H=c===f.id?{start:q.start,end:q.end}:p.end<=m.start?{start:q.end,end:q.end}:{start:q.start,end:q.start},H.innerR=H.r=v):f.graphic&&(e===f.id?O={innerR:v,r:v}:u&&(O=u.end<=C.start?{innerR:v,r:v,start:q.end,end:q.end}:{innerR:v,r:v,start:q.start,end:q.start}));C=H}else O=m,C={};H=[m.plotX,m.plotY];if(!f.node.isLeaf)if(c===f.id){var x=A[c];x=x.parent}else x=f.id;g(f,{shapeExisting:m,tooltipPos:H,drillId:x,
            name:""+(f.name||f.id||f.index),plotX:m.plotX,plotY:m.plotY,value:k.val,isNull:!z});x=f.options;k=y(m)?m:{};x=y(x)?x.dataLabels:{};h=J(y(h)?h.dataLabels:{})[0];h=r({style:{}},h,x);x=h.rotationMode;if(!l(h.rotation)){if("auto"===x||"circular"===x)if(1>f.innerArcLength&&f.outerArcLength>k.radius){var I=0;f.dataLabelPath&&"circular"===x&&(h.textPath={enabled:!0})}else 1<f.innerArcLength&&f.outerArcLength>1.5*k.radius?"circular"===x?h.textPath={enabled:!0,attributes:{dy:5}}:x="parallel":(f.dataLabel&&
        f.dataLabel.textPathWrapper&&"circular"===x&&(h.textPath={enabled:!1}),x="perpendicular");"auto"!==x&&"circular"!==x&&(I=k.end-(k.end-k.start)/2);h.style.width="parallel"===x?Math.min(2.5*k.radius,(f.outerArcLength+f.innerArcLength)/2):k.radius;"perpendicular"===x&&f.series.chart.renderer.fontMetrics(h.style.fontSize).h>f.outerArcLength&&(h.style.width=1);h.style.width=Math.max(h.style.width-2*(h.padding||0),1);I=I*B%180;"parallel"===x&&(I-=90);90<I?I-=180:-90>I&&(I+=180);h.rotation=I}h.textPath&&
        (0===f.shapeExisting.innerR&&h.textPath.enabled?(h.rotation=0,h.textPath.enabled=!1,h.style.width=Math.max(2*f.shapeExisting.r-2*(h.padding||0),1)):f.dlOptions&&f.dlOptions.textPath&&!f.dlOptions.textPath.enabled&&"circular"===x&&(h.textPath.enabled=!0),h.textPath.enabled&&(h.rotation=0,h.style.width=Math.max((f.outerArcLength+f.innerArcLength)/2-2*(h.padding||0),1)));0===h.rotation&&(h.rotation=.001);f.dlOptions=h;if(!E&&z){E=!0;var G=F}f.draw({animatableAttribs:O,attribs:g(C,!t.styledMode&&b.pointAttribs(f,
                f.selected&&"select")),onComplete:G,group:n,renderer:D,shapeType:"arc",shapeArgs:m})});m&&E?(b.hasRendered=!1,b.options.dataLabels.defer=!0,G.prototype.drawDataLabels.call(b),b.hasRendered=!0,z&&F()):G.prototype.drawDataLabels.call(b)},pointAttribs:D.column.prototype.pointAttribs,layoutAlgorithm:function(b,d,g){var f=b.start,a=b.end-f,c=b.val,e=b.x,k=b.y,h=g&&y(g.levelSize)&&l(g.levelSize.value)?g.levelSize.value:0,u=b.r,n=u+h,m=g&&l(g.slicedOffset)?g.slicedOffset:0;return(d||[]).reduce(function(b,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     d){var g=1/c*d.val*a,l=f+g/2,p=e+Math.cos(l)*m;l=k+Math.sin(l)*m;d={x:d.sliced?p:e,y:d.sliced?l:k,innerR:u,r:n,radius:h,start:f,end:f+g};b.push(d);f=d.end;return b},[])},setShapeArgs:function(b,d,g){var f=[],a=g[b.level+1];b=b.children.filter(function(a){return a.visible});f=this.layoutAlgorithm(d,b,a);b.forEach(function(a,b){b=f[b];var c=b.start+(b.end-b.start)/2,d=b.innerR+(b.r-b.innerR)/2,e=b.end-b.start;d=0===b.innerR&&6.28<e?{x:b.x,y:b.y}:{x:b.x+Math.cos(c)*d,y:b.y+Math.sin(c)*d};var k=a.val?
            a.childrenTotal>a.val?a.childrenTotal:a.val:a.childrenTotal;this.points[a.i]&&(this.points[a.i].innerArcLength=e*b.innerR,this.points[a.i].outerArcLength=e*b.r);a.shapeArgs=r(b,{plotX:d.x,plotY:d.y+4*Math.abs(Math.cos(c))});a.values=r(b,{val:k});a.children.length&&this.setShapeArgs(a,a.values,g)},this)},translate:function(){var b=this,d=b.options,g=b.center=L.call(b),n=b.startAndEndRadians=t(d.startAngle,d.endAngle),a=g[3]/2,c=g[2]/2-a,e=P(b),l=b.nodeMap,h=l&&l[e],m={};b.shapeRoot=h&&h.shapeArgs;
            G.prototype.translate.call(b);var y=b.tree=b.getTree();b.renderTraverseUpButton(e);l=b.nodeMap;h=l[e];var w=p(h.parent)?h.parent:"";w=l[w];var r=N(h);var z=r.from,B=r.to;r=U({from:z,levels:b.options.levels,to:B,defaults:{colorByPoint:d.colorByPoint,dataLabels:d.dataLabels,levelIsConstant:d.levelIsConstant,levelSize:d.levelSize,slicedOffset:d.slicedOffset}});r=M(r,{diffRadius:c,from:z,to:B});v(y,{before:F,idRoot:e,levelIsConstant:d.levelIsConstant,mapOptionsToLevel:r,mapIdToNode:l,points:b.points,
                series:b});d=l[""].shapeArgs={end:n.end,r:a,start:n.start,val:h.val,x:g[0],y:g[1]};this.setShapeArgs(w,d,r);b.mapOptionsToLevel=r;b.data.forEach(function(a){m[a.id]&&q(31,!1,b.chart);m[a.id]=!0});m={}},alignDataLabel:function(b,d,g){if(!g.textPath||!g.textPath.enabled)return D.treemap.prototype.alignDataLabel.apply(this,arguments)},animate:function(b){var d=this.chart,f=[d.plotWidth/2,d.plotHeight/2],g=d.plotLeft,a=d.plotTop;d=this.group;b?(b={translateX:f[0]+g,translateY:f[1]+a,scaleX:.001,scaleY:.001,
            rotation:10,opacity:.01},d.attr(b)):(b={translateX:g,translateY:a,scaleX:1,scaleY:1,rotation:0,opacity:1},d.animate(b,this.options.animation))},utils:{calculateLevelSizes:M,getLevelFromAndTo:N,range:E}},{draw:m,shouldDraw:function(){return!this.isNull},isValid:function(){return!0},getDataLabelPath:function(b){var d=this.series.chart.renderer,f=this.shapeExisting,g=f.start,a=f.end,c=g+(a-g)/2;c=0>c&&c>-Math.PI||c>Math.PI;var e=f.r+(b.options.distance||0);g===-Math.PI/2&&w(a)===w(1.5*Math.PI)&&(g=-Math.PI+
            Math.PI/360,a=-Math.PI/360,c=!0);if(a-g>Math.PI){c=!1;var l=!0}this.dataLabelPath&&(this.dataLabelPath=this.dataLabelPath.destroy());this.dataLabelPath=d.arc({open:!0,longArc:l?1:0}).add(b);this.dataLabelPath.attr({start:c?g:a,end:c?a:g,clockwise:+c,x:f.x,y:f.y,r:(e+f.innerR)/2});return this.dataLabelPath}})});m(d,"masters/modules/sunburst.src.js",[],function(){})});
//# sourceMappingURL=sunburst.js.map