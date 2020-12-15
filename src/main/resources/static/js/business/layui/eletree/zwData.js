layui.use(['tree'], function() {
    var tree = layui.tree
        , layer = layui.layer;

    $(function () {
        loadZwData();
    });

    function loadZwData() {
        $("body").append('<div id="zwTree" style="display: none;"><div class="layui-inline"><input type="text" class="layui-input" id="tree_name" placeholder="请输入职位名称"  autocomplete="off" style="margin: 5px 5px 0 5px;width: 340px" /></div><button class="layui-btn" id="btn_query" style="margin: 5px 5px 0 5px;background-color: #3598dc">搜索</button><div id="zwTreeContent" class="demo-tree demo-tree-box"></div></div>');
        $.ajax({
            type: "post",
            url: "/layui/eletree/zw-data",
            success: function(data) {
                var el6 = tree.render({
                    elem: '#zwTreeContent' //默认是点击节点可进行收缩
                    ,data: data
                    ,click: function(obj){
                        var data = obj.data;  //获取当前点击的节点数据
                        if (typeof(data.children) == "undefined" || data.children.length === 0) {
                            $("#cce511").val(data.title);
                            $("#cce510").val(data.id);
                            layer.closeAll();
                        }
                    }
                });

                $("#btn_query").click(function(){
                    var name = $("#tree_name").val(); //搜索值
                    if (!name) {
                        return; //无搜索值返回
                    }
                    var elem = $("#zwTreeContent").find('.layui-tree-txt');
                    var $target = elem.filter(':contains(' + name + ')');
                    if (rgb2hex($target.css("color")) == "#3598dc") {
                        return;   // 如果已经高亮选择了，则返回
                    }

                    // 设置颜色
                    elem.css('color', ''); //搜索文本与设置默认颜色
                    $target.css('color', '#3598dc');

                    // 展开
                    $target.parents(".layui-tree-set").each(function () {
                        $(this).find('.layui-tree-iconClick').eq(0).click();
                    });
                });

                function rgb2hex(rgb) {
                    rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
                    function hex(x) {
                        return ("0" + parseInt(x).toString(16)).slice(-2);
                    }
                    return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
                }
            }
        });
    }

});