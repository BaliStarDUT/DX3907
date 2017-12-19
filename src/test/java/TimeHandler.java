import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeHandler extends EventAdapter{
    public TimeHandler() {
    }
    public void onWrite(Request request, Response response) throws Exception {
        String command = new String(request.getDataInput());
        String time = null;
        Date date = new Date();
        // 判断查询命令
        if (command.equals("GB")) {
            // 中文格式
            DateFormat cnDate = DateFormat.getDateTimeInstance(DateFormat.LONG,
                    DateFormat.LONG, Locale.CHINA);
            time = cnDate.format(date);
        }
        else {
            // 英文格式
            DateFormat enDate = DateFormat.getDateTimeInstance(DateFormat.LONG,
                    DateFormat.LONG, Locale.US);
            time = enDate.format(date);
        }
        response.send((time+Thread.currentThread().getName()).getBytes());
    }

    public void onAccept() throws Exception {
        System.out.println("#onAccept()");
    }

    public void onAccepted(Request request) throws Exception {
        System.out.println("#onAccepted()");
    }

    //    public void onRead(Request request) throws Exception {
//        byte[] rspData = data;
//        if (new String (data).equalsIgnoreCase("query")) {
//            rspData = new java.util.Date().toString().getBytes();
//        }
//        request.attach(rspData);
//        System.out.println("#onRead()");
//    }
    public void onRead(Request request) throws Exception {
//        System.out.println("Received: " + new String(request.attachment().toString()));
        System.out.println("Received: ");

    }

    public void onClosed(Request request) throws Exception {
        System.out.println("#onClosed()");
    }

    public void onError(String error) {
        System.out.println("#onAError(): " + error);
    }
}
