function format(s){
    var y,m,d,h,mm,s;
    y = new Number(s.substring(0,4));
    m = new Number(s.substring(5,7));
    d = new Number(s.substring(8,10));
    if(s.length==19){
        h = new Number(s.substring(11,13));
        mm = new Number(s.substring(14,16));
        s = new Number(s.substring(17,19));
        return new Date(y,m-1,d,h,mm,s);
    }
    return new Date(y,m-1,d);
}