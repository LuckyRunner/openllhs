$(document).on("click", "a[name='view_map']", function () {
    $("#expression").remove();
    $("a[name='hide_expression']").html("<span class=\"fa fa-globe\"></span>");
    $("a[name='hide_expression']").attr("name","view_map");
    $(this).html("<span class=\"glyphicon glyphicon-minus\"></span>");
    $(this).attr("name","hide_map");

    var l = $(this).parent().parent().children().length;
    var a = '<tr id="snp_map">' + '<td colspan="'+l+'"><div id="exp_bar" style="width: 100%;height: 600px"></div></td>'+'</tr>';

    $(this).parent().parent().after(a);
    // +$(this).data("id")
    $.ajax({
        url:"https://bigd.big.ac.cn/hpc/browse/snp_map?rsID="+$(this).data("id"),
        type: "GET",
        dataType:"json",
        success:function(r) {
            if (r.status == "error"){
                $("#exp_bar").height(20);
                $("#exp_bar").append("<span>Sorry! Data is not available for this gene.</span>")
            } else {

                var data = r.data,
                    demColor = 'rgba(74,131,240,0.80)',
                    repColor = 'rgba(220,71,71,0.80)',
                    libColor = 'rgba(240,190,50,0.80)',
                    grnColor = 'rgba(90,200,90,0.80)',
                    idNameMap = {};

                Highcharts.each(data, function (row) {
                    idNameMap[row[0]] = row[0];
                });

                var chart = Highcharts.mapChart('exp_bar', {

                    title : {
                        text : 'percentage'
                    },

                    colorAxis: {
                        dataClasses: [{
                            from: -1,
                            to: 0,
                            color: repColor,
                            name: 'A'
                        }, {
                            from: 0,
                            to: 1,
                            color: demColor,
                            name: 'G'
                        }, {
                            from: 2,
                            to: 3,
                            name: 'T',
                            color: libColor
                        }, {
                            from: 3,
                            to: 4,
                            name: 'C',
                            color: grnColor
                        }]
                    },

                    series : [{
                        mapData: Highcharts.maps['custom/world'],
                        data : data,
                        joinBy: ['name'],
                        name: '百分比',
                        keys: ['name', 'demVotes', 'repVotes', 'libVotes', 'grnVotes',
                            'sumVotes', 'value', 'pieOffset'],
                        tooltip: {
                            headerFormat: '',
                            pointFormatter: function () {
                                return '<b>' + this.name+'</b><br/>' +
                                    'people group: ' +this.demVotes +
                                    '<br/>'+this.repVotes +': '+this.grnVotes+
                                    '<br/>'+this.libVotes +': '+this.sumVotes;
                            }
                        }
                    }]
                });

            }
        }
    })

});

$(document).on("click", "a[name='hide_map']", function () {
    $("#snp_map").remove();
    $(this).html("<span class=\"fa fa-globe\"></span>");
    $(this).attr("name","view_map");
});