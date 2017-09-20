package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by chengww on 2017/6/5.
 */

public class BankInfo {

    /**
     * province : [{"city":[{"name":"黄浦区","id":378},{"name":"卢湾区","id":379},{"name":"徐汇区","id":380},{"name":"长宁区","id":381},{"name":"静安区","id":382},{"name":"普陀区","id":383},{"name":"闸北区","id":384},{"name":"虹口区","id":385},{"name":"杨浦区","id":386},{"name":"闵行区","id":387},{"name":"宝山区","id":388},{"name":"嘉定区","id":389},{"name":"浦东新区","id":390},{"name":"金山区","id":391},{"name":"松江区","id":392},{"name":"青浦区","id":393},{"name":"南汇区","id":394},{"name":"奉贤区","id":395},{"name":"崇明县","id":396},{"name":"城桥镇","id":397}],"name":"上海市","id":9},{"city":[{"name":"昆明市","id":266},{"name":"曲靖市","id":267},{"name":"玉溪市","id":268},{"name":"保山市","id":269},{"name":"昭通市","id":270},{"name":"丽江市","id":271},{"name":"思茅市","id":272},{"name":"临沧市","id":273},{"name":"楚雄彝族自治州","id":274},{"name":"红河哈尼族彝族自治州","id":275},{"name":"文山壮族苗族自治州","id":276},{"name":"西双版纳傣族自治州","id":277},{"name":"大理白族自治州","id":278},{"name":"德宏傣族景颇族自治州","id":279},{"name":"怒江傈僳族自治州","id":280},{"name":"迪庆藏族自治州","id":281}],"name":"云南省","id":25},{"city":[{"name":"呼和浩特市","id":25},{"name":"包头市","id":26},{"name":"乌海市","id":27},{"name":"赤峰市","id":28},{"name":"通辽市","id":29},{"name":"鄂尔多斯市","id":30},{"name":"呼伦贝尔市","id":31},{"name":"巴彦淖尔市","id":32},{"name":"乌兰察布市","id":33},{"name":"兴安盟","id":34},{"name":"锡林郭勒盟","id":35},{"name":"阿拉善盟","id":36}],"name":"内蒙古自治区","id":5},{"city":[{"name":"崇文区","id":341},{"name":"宣武区","id":342},{"name":"朝阳区","id":343},{"name":"丰台区","id":344},{"name":"石景山区","id":345},{"name":"海淀区","id":346},{"name":"门头沟区","id":347},{"name":"房山区","id":348},{"name":"通州区","id":349},{"name":"顺义区","id":350},{"name":"昌平区","id":351},{"name":"大兴区","id":352},{"name":"怀柔区","id":353},{"name":"平谷区","id":354},{"name":"密云县","id":355},{"name":"延庆县","id":356},{"name":"延庆镇","id":357}],"name":"北京市","id":1},{"city":[{"name":"长春市","id":51},{"name":"吉林市","id":52},{"name":"四平市","id":53},{"name":"辽源市","id":54},{"name":"通化市","id":55},{"name":"白山市","id":56},{"name":"松原市","id":57},{"name":"白城市","id":58},{"name":"延边朝鲜族 自治州","id":59}],"name":"吉林省","id":7},{"city":[{"name":"成都市","id":236},{"name":"自贡市","id":237},{"name":"攀枝花市","id":238},{"name":"泸州市","id":239},{"name":"德阳市","id":240},{"name":"绵阳市","id":241},{"name":"广元市","id":242},{"name":"遂宁市","id":243},{"name":"内江市","id":244},{"name":"乐山市","id":245},{"name":"南充市","id":246},{"name":"眉山市","id":247},{"name":"宜宾市","id":248},{"name":"广安市","id":249},{"name":"达州市","id":250},{"name":"雅安市","id":251},{"name":"巴中市","id":252},{"name":"资阳市","id":253},{"name":"阿坝藏族羌族自治州","id":254},{"name":"甘孜藏族自治州","id":255},{"name":"凉山彝族自治州","id":256}],"name":"四川省","id":23},{"city":[{"name":"和平区","id":358},{"name":"河东区","id":359},{"name":"河西区","id":360},{"name":"南开区","id":361},{"name":"河北区","id":362},{"name":"红桥区","id":363},{"name":"塘沽区","id":364},{"name":"汉沽区","id":365},{"name":"大港区","id":366},{"name":"东丽区","id":367},{"name":"西青区","id":368},{"name":"津南区","id":369},{"name":"北辰区","id":370},{"name":"武清区","id":371},{"name":"宝坻区","id":372},{"name":"蓟县","id":373},{"name":"宁河县","id":374},{"name":"芦台镇","id":375},{"name":"静海县","id":376},{"name":"静海镇","id":377}],"name":"天津市","id":2},{"city":[{"name":"银川市","id":321},{"name":"石嘴山市","id":322},{"name":"吴忠市","id":323},{"name":"固原市","id":324},{"name":" 中卫市","id":325}],"name":"宁夏回族自治区","id":30},{"city":[{"name":"合肥市","id":98},{"name":"芜湖市","id":99},{"name":"蚌埠市","id":100},{"name":"淮南市","id":101},{"name":"马鞍山市","id":102},{"name":"淮北市","id":103},{"name":"铜陵市","id":104},{"name":"安庆市","id":105},{"name":"黄山市","id":106},{"name":"滁州市","id":107},{"name":"阜阳市","id":108},{"name":"宿州市","id":109},{"name":"巢湖市","id":110},{"name":"六安市","id":111},{"name":"亳州市","id":112},{"name":"池州市","id":113},{"name":"宣城市","id":114}],"name":"安徽省","id":12},{"city":[{"name":"济南市","id":135},{"name":"青岛市","id":136},{"name":"淄博市","id":137},{"name":"枣庄市","id":138},{"name":"东营市","id":139},{"name":"烟台市","id":140},{"name":"潍坊市","id":141},{"name":"济宁市","id":142},{"name":"泰安市","id":143},{"name":"威海市","id":144},{"name":"日照市","id":145},{"name":"莱芜市","id":146},{"name":"临沂市","id":147},{"name":"德州市","id":148},{"name":"聊城市","id":149},{"name":"滨州市","id":150},{"name":"菏泽市","id":151}],"name":"山东省","id":15},{"city":[{"name":"太原市","id":14},{"name":"大同市","id":15},{"name":"阳泉市","id":16},{"name":"长治市","id":17},{"name":"晋城市","id":18},{"name":"朔州市","id":19},{"name":"晋中市","id":20},{"name":"运城市","id":21},{"name":"忻州市","id":22},{"name":"临汾市","id":23},{"name":"吕梁市","id":24}],"name":"山西省","id":4},{"city":[{"name":"广州市","id":197},{"name":"韶关市","id":198},{"name":"深圳市","id":199},{"name":"珠海市","id":200},{"name":"汕头市","id":201},{"name":"佛山市","id":202},{"name":" 江门市","id":203},{"name":"湛江市","id":204},{"name":"茂名市","id":205},{"name":"肇庆市","id":206},{"name":"惠州市","id":207},{"name":"梅州市","id":208},{"name":"汕尾市","id":209},{"name":"河源市","id":210},{"name":"阳江市","id":211},{"name":" 清远市","id":212},{"name":"东莞市","id":213},{"name":"中山市","id":214},{"name":"潮州市","id":215},{"name":"揭阳市","id":216},{"name":"云浮市","id":217}],"name":"广东省","id":19},{"city":[{"name":"南宁市","id":218},{"name":"柳州市","id":219},{"name":"桂林市","id":220},{"name":" 梧州市","id":221},{"name":"北海市","id":222},{"name":"防城港市","id":223},{"name":"钦州市","id":224},{"name":"贵港市","id":225},{"name":"玉林市","id":226},{"name":"百色市","id":227},{"name":"贺州市","id":228},{"name":"河池市","id":229},{"name":"来宾市","id":230},{"name":"崇左市","id":231}],"name":"广西壮族自治区","id":20},{"city":[{"name":"乌鲁木齐市","id":326},{"name":"克拉玛依市","id":327},{"name":"吐鲁番地区","id":328},{"name":"哈密地区","id":329},{"name":"昌吉回族自治州","id":330},{"name":"博尔塔拉蒙古自治州","id":331},{"name":"巴音郭 楞蒙古自治州","id":332},{"name":"阿克苏地区","id":333},{"name":"克孜勒苏柯尔克孜自治州","id":334},{"name":"喀什地区","id":335},{"name":"和田地区","id":336},{"name":"伊犁哈萨克自治州","id":337},{"name":"塔城地区","id":338},{"name":"阿勒泰地区","id":339},{"name":"省直辖行政单位","id":340}],"name":"新疆维吾尔自治区","id":31},{"city":[{"name":"南京市","id":74},{"name":"无锡市","id":75},{"name":"徐州市","id":76},{"name":"常州市","id":77},{"name":"苏州市","id":78},{"name":"南通市","id":79},{"name":"连云港市","id":80},{"name":"淮安市","id":81},{"name":"盐城市","id":82},{"name":"扬州市","id":83},{"name":"镇江市","id":84},{"name":"泰州市","id":85},{"name":"宿迁市","id":86}],"name":"江苏省","id":10},{"city":[{"name":"南昌市","id":124},{"name":"景德镇市","id":125},{"name":"萍乡市","id":126},{"name":"九江市","id":127},{"name":"新余市","id":128},{"name":"鹰潭市","id":129},{"name":"赣州市","id":130},{"name":"吉安市","id":131},{"name":"宜春市","id":132},{"name":"抚州市","id":133},{"name":"上饶市","id":134}],"name":"江西省","id":14},{"city":[{"name":"石家庄市","id":3},{"name":"唐山市","id":4},{"name":"秦皇岛市","id":5},{"name":"邯郸市","id":6},{"name":"邢台市","id":7},{"name":"保定市","id":8},{"name":"张家口市","id":9},{"name":"承德市","id":10},{"name":"沧州市","id":11},{"name":"廊坊市","id":12},{"name":"衡水市","id":13}],"name":"河北省","id":3},{"city":[{"name":"郑州市","id":152},{"name":"开封市","id":153},{"name":"洛阳市","id":154},{"name":"平顶山市","id":155},{"name":"安阳市","id":156},{"name":"鹤壁市","id":157},{"name":"新乡市","id":158},{"name":"焦作市","id":159},{"name":"濮阳市","id":160},{"name":"许昌市","id":161},{"name":"漯河市","id":162},{"name":"三门峡市","id":163},{"name":"南阳市","id":164},{"name":"商丘市","id":165},{"name":"信阳市","id":166},{"name":"周口市","id":167},{"name":"驻马店市","id":168}],"name":"河南省","id":16},{"city":[{"name":"杭州市","id":87},{"name":"宁波市","id":88},{"name":"温州市","id":89},{"name":"嘉兴市","id":90},{"name":"湖州市","id":91},{"name":"绍兴市","id":92},{"name":"金华市","id":93},{"name":"衢州市","id":94},{"name":"舟山市","id":95},{"name":"台州市","id":96},{"name":"丽水市","id":97}],"name":"浙江省","id":11},{"city":[{"name":"海口市","id":232},{"name":"三亚市","id":233},{"name":"省直辖县级行政单位","id":234}],"name":"海南省","id":21},{"city":[{"name":"武汉市","id":169},{"name":"黄石市","id":170},{"name":"十堰市","id":171},{"name":"宜昌市","id":172},{"name":"襄樊市","id":173},{"name":"鄂州市","id":174},{"name":"荆门市","id":175},{"name":"孝感市","id":176},{"name":"荆州市","id":177},{"name":"黄冈市","id":178},{"name":"咸宁市","id":179},{"name":"随州市","id":180},{"name":"恩施土家族苗族自治州","id":181},{"name":"省直辖行政单位","id":182}],"name":"湖北省","id":17},{"city":[{"name":"长沙市","id":183},{"name":"株洲市","id":184},{"name":"湘潭市","id":185},{"name":"衡阳市","id":186},{"name":"邵阳市","id":187},{"name":"岳阳市","id":188},{"name":"常德市","id":189},{"name":"张家界市","id":190},{"name":"益阳市","id":191},{"name":"郴州市","id":192},{"name":"永州市","id":193},{"name":"怀化市","id":194},{"name":"娄底市","id":195},{"name":"湘西土家族苗族自治州","id":196}],"name":"湖南省","id":18},{"city":[{"name":"兰州市","id":299},{"name":"嘉峪关市","id":300},{"name":"金昌市","id":301},{"name":"白银市","id":302},{"name":"天水市","id":303},{"name":"武威市","id":304},{"name":"张掖市","id":305},{"name":"平凉市","id":306},{"name":"酒泉市","id":307},{"name":"庆阳市","id":308},{"name":"定西市","id":309},{"name":"陇南市","id":310},{"name":"临夏回族自治州","id":311},{"name":"甘南藏族自治州","id":312}],"name":"甘肃省","id":28},{"city":[{"name":"福州市","id":115},{"name":"厦门市","id":116},{"name":"莆田市","id":117},{"name":"三明市","id":118},{"name":"泉州市","id":119},{"name":"漳州市","id":120},{"name":"南平市","id":121},{"name":"龙岩市","id":122},{"name":"宁德市","id":123}],"name":"福建省","id":13},{"city":[{"name":"拉萨市","id":282},{"name":"昌都地区","id":283},{"name":"山南 地区","id":284},{"name":"日喀则地区","id":285},{"name":"那曲地区","id":286},{"name":"阿里地区","id":287},{"name":"林芝地区","id":288}],"name":"西藏自治区","id":26},{"city":[{"name":"贵阳市","id":257},{"name":"六盘水市","id":258},{"name":"遵义市","id":259},{"name":"安顺市","id":260},{"name":"铜仁地区","id":261},{"name":"黔西南布依族苗族自治州","id":262},{"name":" 毕节地区","id":263},{"name":"黔东南苗族侗族自治州","id":264},{"name":"黔南布依族苗族自治州","id":265}],"name":"贵州省","id":24},{"city":[{"name":"沈阳市","id":37},{"name":"大连市","id":38},{"name":"鞍山市","id":39},{"name":"抚顺市","id":40},{"name":"本溪市","id":41},{"name":"丹东市","id":42},{"name":"锦州市","id":43},{"name":"营口市","id":44},{"name":"阜新市","id":45},{"name":"辽阳市","id":46},{"name":"盘锦市","id":47},{"name":"铁岭市","id":48},{"name":"朝阳市","id":49},{"name":"葫芦岛市","id":50}],"name":"辽宁省","id":6},{"city":[{"name":"渝中区","id":398},{"name":"大渡口区","id":399},{"name":"江北区","id":400},{"name":"沙坪坝区","id":401},{"name":"九龙坡区","id":402},{"name":"南岸区","id":403},{"name":"北碚区","id":404},{"name":"万盛区","id":405},{"name":"双桥区","id":406},{"name":"渝北区","id":407},{"name":"巴南区","id":408},{"name":"万州区","id":409},{"name":"涪陵区","id":410},{"name":"黔江区","id":411},{"name":"长寿区","id":412},{"name":"合川市","id":413},{"name":"永川区市","id":414},{"name":"江津市","id":415},{"name":"南川市","id":416},{"name":"綦江县","id":417},{"name":"潼南县","id":418},{"name":"铜梁县","id":419},{"name":"大足县","id":420},{"name":"荣昌县","id":421},{"name":"璧山县","id":422},{"name":"垫江县","id":423},{"name":"武隆县","id":424},{"name":"丰都县","id":425},{"name":"城口县","id":426},{"name":"梁平县","id":427},{"name":"开县","id":428},{"name":"巫溪县","id":429},{"name":"巫山县","id":430},{"name":"奉节县","id":431},{"name":"云阳县","id":432},{"name":"忠县","id":433},{"name":"石柱土家族自治县","id":434},{"name":"彭水苗族土家族自治县","id":435},{"name":"酉阳土家族苗族自治县","id":436},{"name":"秀山土家族苗族自治县","id":437}],"name":"重庆市","id":22},{"city":[{"name":"西安市","id":289},{"name":"铜川市","id":290},{"name":"宝鸡市","id":291},{"name":"咸阳市","id":292},{"name":"渭南市","id":293},{"name":"延安市","id":294},{"name":"汉中市","id":295},{"name":"榆林市","id":296},{"name":"安康市","id":297},{"name":"商洛市","id":298}],"name":"陕西省","id":27},{"city":[{"name":"西宁市","id":313},{"name":"海东地区","id":314},{"name":"海北藏族自治州","id":315},{"name":"黄南藏族自治州","id":316},{"name":"海南藏族自治州","id":317},{"name":"果洛藏族自治州","id":318},{"name":"玉树藏族自治州","id":319},{"name":"海西蒙古族藏族自治州","id":320}],"name":"青海省","id":29},{"city":[{"name":"哈尔滨市","id":60},{"name":"齐齐哈尔市","id":61},{"name":"鸡西市","id":62},{"name":"鹤岗市","id":63},{"name":"双鸭山市","id":64},{"name":"大庆市","id":65},{"name":"伊春市","id":66},{"name":"佳木斯市","id":67},{"name":"七台河市","id":68},{"name":"牡丹江市","id":69},{"name":"黑河市","id":70},{"name":"绥化市","id":71},{"name":"大兴安岭地区","id":72}],"name":"黑龙江省","id":8}]
     * is_bind_flt_card : true
     * already_has_city : {"id":"55","name":"通化市"}
     * show_unwithdraw_notice : false
     * banks : [{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/3e93647d/bank-3e93647d-2a74-49ea-a94a-5447ef8490d9-1480416189.jpg","code":"ICBC","name":"中国工商银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/f27603aa/bank-f27603aa-6712-4f2a-a698-be59abf6b9ad-1480416213.png","code":"CCB","name":"中国建设银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/0896be36/bank-0896be36-dc51-4dca-9332-64874ffb2147-1480416375.png","code":"CMB","name":"招商银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/363cde07/bank-363cde07-6edc-4b53-bf3c-919bdcc26682-1480416360.png","code":"BCOM","name":"中国交通银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png","code":"ABC","name":"中国农业银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/6a5b22bc/bank-6a5b22bc-4bf5-463f-93f4-99c298c53595-1480416228.png","code":"BOC","name":"中国银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/5067c144/bank-5067c144-fdf6-427d-85a1-b0338a729547-1480416257.png","code":"CITIC","name":"中信银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/f709f80e/bank-f709f80e-d9ac-4973-a1bf-fa84da74cf74-1480416332.png","code":"CMBC","name":"民生银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/7fb1008c/bank-7fb1008c-3dd0-4cf4-bdd7-5006695a4592-1480416292.png","code":"PSBC","name":"中国邮政储蓄银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/9a77b789/bank-9a77b789-7b87-4094-9a59-fcc1938ef480-1480416319.png","code":"SPDB","name":"浦东发展银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/84386db0/bank-84386db0-032f-4197-b427-f1854e6d3d0b-1480416266.png","code":"CEB","name":"中国光大银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/0af34029/bank-0af34029-6511-4a4a-9515-b10c392499b1-1480416241.png","code":"CIB","name":"兴业银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/fd6114ce/bank-fd6114ce-9574-42a7-bcfc-df1f1b435c63-1480416346.png","code":"HXB","name":"华夏银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/4c16a13d/bank-4c16a13d-b009-4c8c-9033-7d3ae8018bad-1480416278.png","code":"PAB","name":"平安银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/d84e09c6/bank-d84e09c6-8185-4072-a28c-fe1c7a934ce8-1480416305.png","code":"SHB","name":"上海银行"},{"logo":"https://o0s106hgi.qnssl.com/media/bank-logo/b003dc8c/bank-b003dc8c-56b8-467e-b0fa-e762e3b7b981-1480416386.png","code":"CGB","name":"广发银行"}]
     * already_has_province : {"id":"7","name":"吉林省"}
     * is_verified : true
     * already_has_province_city_flag : true
     * bind_bank_list : [{"card_type":"withdraw","bank_name":"中国农业银行","card_num":"6228***********9116","id":88,"bank_logo":"https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png"},{"card_type":"fund","bank_name":"建设银行","card_num":"6227***********8219","id":8,"bank_logo":"https://o0s106hgi.qnssl.com/media/bank-logo/f27603aa/bank-f27603aa-6712-4f2a-a698-be59abf6b9ad-1480416213.png"}]
     */

    private boolean is_bind_flt_card;
    private AlreadyHasCityBean already_has_city;
    private boolean show_unwithdraw_notice;
    private AlreadyHasProvinceBean already_has_province;
    private boolean is_verified;
    private boolean already_has_province_city_flag;
    private List<ProvinceBean> province;
    private List<BanksBean> banks;
    private List<BindBankListBean> bind_bank_list;

    public boolean isIs_bind_flt_card() {
        return is_bind_flt_card;
    }

    public void setIs_bind_flt_card(boolean is_bind_flt_card) {
        this.is_bind_flt_card = is_bind_flt_card;
    }

    public AlreadyHasCityBean getAlready_has_city() {
        return already_has_city;
    }

    public void setAlready_has_city(AlreadyHasCityBean already_has_city) {
        this.already_has_city = already_has_city;
    }

    public boolean isShow_unwithdraw_notice() {
        return show_unwithdraw_notice;
    }

    public void setShow_unwithdraw_notice(boolean show_unwithdraw_notice) {
        this.show_unwithdraw_notice = show_unwithdraw_notice;
    }

    public AlreadyHasProvinceBean getAlready_has_province() {
        return already_has_province;
    }

    public void setAlready_has_province(AlreadyHasProvinceBean already_has_province) {
        this.already_has_province = already_has_province;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public boolean isAlready_has_province_city_flag() {
        return already_has_province_city_flag;
    }

    public void setAlready_has_province_city_flag(boolean already_has_province_city_flag) {
        this.already_has_province_city_flag = already_has_province_city_flag;
    }

    public List<ProvinceBean> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceBean> province) {
        this.province = province;
    }

    public List<BanksBean> getBanks() {
        return banks;
    }

    public void setBanks(List<BanksBean> banks) {
        this.banks = banks;
    }

    public List<BindBankListBean> getBind_bank_list() {
        return bind_bank_list;
    }

    public void setBind_bank_list(List<BindBankListBean> bind_bank_list) {
        this.bind_bank_list = bind_bank_list;
    }

    public static class AlreadyHasCityBean {
        /**
         * id : 55
         * name : 通化市
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class AlreadyHasProvinceBean {
        /**
         * id : 7
         * name : 吉林省
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ProvinceBean {
        /**
         * city : [{"name":"黄浦区","id":378},{"name":"卢湾区","id":379},{"name":"徐汇区","id":380},{"name":"长宁区","id":381},{"name":"静安区","id":382},{"name":"普陀区","id":383},{"name":"闸北区","id":384},{"name":"虹口区","id":385},{"name":"杨浦区","id":386},{"name":"闵行区","id":387},{"name":"宝山区","id":388},{"name":"嘉定区","id":389},{"name":"浦东新区","id":390},{"name":"金山区","id":391},{"name":"松江区","id":392},{"name":"青浦区","id":393},{"name":"南汇区","id":394},{"name":"奉贤区","id":395},{"name":"崇明县","id":396},{"name":"城桥镇","id":397}]
         * name : 上海市
         * id : 9
         */

        private String name;
        private int id;
        private List<CityBean> city;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * name : 黄浦区
             * id : 378
             */

            private String name;
            private int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }

    public static class BanksBean {
        /**
         * logo : https://o0s106hgi.qnssl.com/media/bank-logo/3e93647d/bank-3e93647d-2a74-49ea-a94a-5447ef8490d9-1480416189.jpg
         * code : ICBC
         * name : 中国工商银行
         */

        private String logo;
        private String code;
        private String name;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class BindBankListBean {
        /**
         * card_type : withdraw
         * bank_name : 中国农业银行
         * card_num : 6228***********9116
         * id : 88
         * bank_logo : https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png
         */

        private String card_type;
        private String bank_name;
        private String card_num;
        private int id;
        private String bank_logo;

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }
    }
}
