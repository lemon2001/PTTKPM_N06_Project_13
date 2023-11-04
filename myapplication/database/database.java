package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.model.DanhGia;
import com.example.myapplication.model.TaiKhoan;
import com.example.myapplication.model.Tin;


public class database extends SQLiteOpenHelper{

    private static String DATABASE_NAME = "aabbccddee";
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    private static int VERSION = 2;

    private static String TABLE_TINTUC = "tintuc";
    private static String ID_TINTUC = "idtintuc";
    private static String TEN_TINTUC = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";

    private static String TABLE_DANH_GIA = "danhgia";
    private static String ID_DANH_GIA = "iddanhgia";
    private static String NOI_DUNG_DANH_GIA = "noidungdanhgia";



    private Context context;

    private String SQLQuery = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";

    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TINTUC +" ( "+ ID_TINTUC +" integer primary key AUTOINCREMENT, "
            + TEN_TINTUC +" TEXT UNIQUE, "
            +NOI_DUNG+" TEXT, "
            +IMAGE+" TEXT, "+ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+"))";

    private String SQLQuery13 = "CREATE TABLE "+ TABLE_DANH_GIA +" ( "+ID_DANH_GIA+" integer primary key AUTOINCREMENT, "
            +NOI_DUNG_DANH_GIA+" TEXT, "
            + TEN_TINTUC +" TEXT, "
            +TEN_TAI_KHOAN+" TEXT,"
            +ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +","+TEN_TAI_KHOAN+" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+","+TEN_TAI_KHOAN+"))";



    private String SQLQuery2 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','admin@gmail.com',2)";




    private String SQLQuery5 = "INSERT INTO tintuc VALUES (null,'Sườn chua ngọt','Bước 1 Sơ chế nguyên liệu\n" +
            "Pha nước ấm với 3 muỗng canh muối và 3 muỗng canh giấm rồi cho sườn vào rửa, chà xát thịt để ra hết chất dơ và máu. Rửa lại nhiều lần với nước cho đến khi nước không còn đục là thịt đã sạch.\n" +
            "\n" +
            "Sau khi rửa xong bạn cắt thịt thành những miếng vừa ăn.','https://cdn.tgdd.vn/Files/2017/03/24/964475/cach-lam-suon-xao-chua-ngot-ngon-bat-bai-vo-cung-don-gian-202202241625033334.jpg',1)";

    private String SQLQuery4 = "INSERT INTO tintuc VALUES (null,'Món gà hấp mía','Rửa sạch và cắt mía thành 3 - 4 đoạn bằng nhau, dùng chày để đập dập rồi bổ đôi từng đoạn mía.\n Rửa sạch và cắt mía thành 3 - 4 đoạn bằng nhau, dùng chày để đập dập rồi bổ đôi từng đoạn mía.','https://cdn.tgdd.vn/2021/09/CookProduct/2412637531970328323721768210389343327681948n-1200x676.jpg',1)";
//    private String SQLQuery5 = "INSERT INTO tintuc VALUES (null,'Tuyển bóng đá nữ Việt có nhất thiết phải cải thiện chiều cao?','Nhiều người đặt ra câu hỏi này khi chứng kiến sự thua thiệt của các nữ tuyển thủ Việt Nam khi đối mặt với hai đối thủ Mỹ và Bồ Đào Nha.\n" +
//            "\n" +
//            "Để trả lời, cần phải nhìn vào tuyển nữ Nhật - đội bóng cũng nằm trong nhóm thấp bé nhẹ cân nhất World Cup nữ 2023.\n" +
//            "\n" +
//            "Trước thềm World Cup 2023, FIFA công bố danh sách cầu thủ của 32 đội tuyển kèm thêm thông tin về chiều cao. Tuyển nữ Việt Nam với chiều cao trung bình 1,61m nằm trong nhóm năm đội thấp nhất giải. Tuyển nữ Nhật cũng nằm trong nhóm thấp bé nhẹ cân nhất giải khi có chiều cao trung bình chỉ là 1,65m.\n" +
//            "\n" +
//            "Không cao nhưng vẫn phải ngước nhìn\n" +
//            "\n" +
//            "Nhưng vẫn như mọi khi, tuyển Nhật tiếp tục chứng tỏ sức mạnh. Họ trở thành đại diện đầu tiên giành vé vượt qua vòng bảng với hai trận thắng chóng vánh trước Zambia và Costa Rica. Khoảng 15 năm qua, thành tích của bóng đá nữ Nhật Bản hầu như chỉ kém Mỹ.\n" +
//            "\n" +
//            "Olympic 2008 đánh dấu sự trỗi dậy của bóng đá nữ Nhật khi vào đến bán kết. 3 năm sau, \"những bông hoa cẩm chướng\" (biệt danh của tuyển nữ Nhật) gây chấn động với kỳ tích vô địch World Cup nữ 2011. Trái với một số dự đoán rằng đó chỉ là thành tích nhất thời, năm 2015 tuyển nữ Nhật lại vào đến chung kết World Cup (thua Mỹ). Trước đó, họ đoạt vị trí á quân ở Olympic 2012.\n" +
//            "\n" +
//            "Trên hành trình đó, những cô gái Nhật chưa bao giờ... cao. Ở World Cup nữ 2011, họ đăng quang với chiều cao trung bình chỉ là 1,62m (một trong những đội bóng thấp nhất giải). \n" +
//
//            "Sau 12 năm, thể hình cầu thủ Nhật vẫn vậy ở World Cup 2023.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/tuyn-nu-nhat-1690598467095733314765.jpg',1)";
//    private String SQLQuery6 = "INSERT INTO tintuc VALUES (null,'Thời tiết còn mưa to, cao tốc Phan Thiết - Dầu Giây có thể ngập tiếp','Mưa dông trong hôm nay vẫn còn duy trì, mưa sẽ kéo dài từ Nam Trung Bộ, Tây Nguyên đến Nam Bộ, do đó khả năng cao tốc Phan Thiết - Dầu Giây sẽ tái diễn cảnh ngập nước.\n" +
//
//            "Trao đổi với Tuổi Trẻ Online, bà Lê Thị Xuân Lan - nguyên phó trưởng phòng dự báo Đài khí tượng thủy văn khu vực Nam Bộ -  cho biết từ chiều hôm qua đến sáng nay Bình Thuận có mưa to, trong đó có khu vực cao tốc Phan Thiết - Dầu Giây.\n" +
//            "\n" +
//            "Nguyên nhân do ảnh hưởng của dải hội tụ nhiệt đới (tồn tại các yếu tố gây mưa) và gió mùa tây nam cường độ mạnh. Tuy không có trạm đo tại vị trí xảy ra ngập trên cao tốc nhưng các trạm gần đó cho thấy lượng mưa ước chừng đạt 50-100mm.\n" +
//            "\n" +
//            "\"Trong hôm nay dải hội tụ nhiệt đới vẫn duy trì, hiện tại các ổ mây dông vẫn đang hình thành trên biển và có xu hướng di chuyển vào đất liền. Khu vực Bình Thuận mưa có giảm nhưng vẫn còn mưa to, do đó khả năng tuyến cao tốc Phan Thiết - Dầu Giây vẫn có thể tái diễn cảnh ngập\", bà Lan nhận định.\n" +
//            "\n" +
//            "Cũng theo bà Lan, việc ngập này không chỉ do mưa tại chỗ mà do mưa to từ đầu nguồn các con sông suối. Nước lũ theo đó tràn về nên không chỉ riêng khu vực cao tốc mà các khu vực lân cận có sông suối đi qua cũng có thể bị ngập.\n" +
//            "Về tình hình chung thời tiết Nam Bộ, từ đêm qua tới sáng nay nhiều nơi có mưa to đến rất to. Điển hình như Tân Điền (Kiên Giang) mưa đạt 216,6mm, Thạnh Trị (Sóc Trăng) 183,8mm, Nàng Mau (Hậu Giang) 125,2mm…\n" +
//            "\n" +
//            "Chiều và đêm nay, khu vực Tây Nguyên và Nam Bộ tiếp tục mưa vừa, mưa to và dông, vài nơi có mưa rất to. Lượng mưa dự báo 20-50mm, có nơi trên 100mm.\n" +
//            "\n" +
//            "Gió mùa tây nam vẫn hoạt động mạnh từ Nam Trung Bộ trở vào Nam Bộ. Tháng 7 mỗi năm thường là tháng gió mùa hoạt động mạnh nhất và gây mưa nhiều nhất.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/cao-toc-16906094285951852986420.jpg',1)";
//    private String SQLQuery7 = "INSERT INTO tintuc VALUES (null,'Bị phạt gần 4 tỉ và đình chỉ hoạt động 12 tháng, lãnh đạo nhiệt điện Phả Lại nói gì?','Với các hành vi vi phạm về bụi và khí thải vượt chuẩn kỹ thuật, Công ty cổ phần nhiệt điện Phả Lại bị phạt tổng mức tiền 3,925 tỉ đồng, đồng thời dừng hoạt động trong thời hạn 12 tháng.\n" +
//
//            "Cả hai dây chuyền sản xuất đều vi phạm\n" +
//            "Theo thông tin từ Cục Cảnh sát phòng, chống tội phạm về môi trường, Bộ Công an, Công ty cổ phần nhiệt điện Phả Lại (phường Phả Lại, TP Chí Linh, Hải Dương) đã có các hành vi vi phạm như thải bụi, khí thải có chứa các thông số môi trường thông thường vượt quy chuẩn kỹ thuật về chất thải tại ống khói dây chuyền I.\n" +
//            "\n" +
//            "Cụ thể, bụi tổng vượt 3,35 lần, SO2 vượt 2,37 lần, NOx vượt 1,11 lần, với lưu lượng 167.949m3/giờ.\n" +
//            "\n" +
//            "Đồng thời công ty còn thải bụi, khí thải có chứa các thông số môi trường thông thường vượt quy chuẩn kỹ thuật về chất thải tại ống khói dây chuyền II. \n" +
//            "\n" +
//            "Chỉ số SO2 vượt 2,58 lần, NOx vượt 1,34 lần, với lưu lượng 331.700m3/giờ.\n" +
//            "\n" +
//            "Với các hành vi vi phạm trên, Cục Cảnh sát phòng, chống tội phạm về môi trường, Bộ Công an đã ra quyết định xử phạt hành chính đối với Công ty cổ phần nhiệt điện Phả Lại tổng mức tiền phạt 3,925 tỉ đồng, đồng thời đình chỉ hoạt động của công ty trong thời hạn 12 tháng.\n','https://cdn.tuoitre.vn/471584752817336320/2023/7/29/paner-khau-hieu-moi-copy-1-16905972915831558144335.jpg',1)";
//    private String SQLQuery8 = "INSERT INTO tintuc VALUES (null,'Bộ trưởng Đặng Quốc Khánh đề nghị TP.HCM hoàn thiện quy hoạch theo hướng nào?','Bộ trưởng Đặng Quốc Khánh đề nghị TP.HCM cần tập trung hoàn thiện quy hoạch và kế hoạch sử dụng đất, bảo đảm tính bền vững và phù hợp với nhu cầu phát triển. Đồng thời, TP cần tăng cường kiểm soát và quản lý đất đai...\n" +
//
//
//            "Đề nghị này được ông Đặng Quốc Khánh - bộ trưởng Bộ Tài nguyên và Môi trường - nêu ra tại lễ kỷ niệm 20 năm thành lập Sở Tài nguyên và Môi trường TP.HCM (18-7-2003 - 18-7-2023) tổ chức sáng 29-7. Tham dự có Chủ tịch UBND TP.HCM Phan Văn Mãi. \n" +
//            "\n" +
//            "Nhân dịp này, ông Đặng Quốc Khánh gửi lời chúc mừng và ghi nhận những nỗ lực, thành quả của các thế hệ lãnh đạo, công chức, viên chức, người lao động ngành tài nguyên và môi trường TP.HCM.\n" +
//            "Xây dựng thành phố xanh, kinh tế xanh, kinh tế tuần hoàn \n" +
//            "Ông đồng thời đề nghị TP.HCM cần quan tâm tập trung hoàn thiện quy hoạch và kế hoạch sử dụng đất, bảo đảm tính bền vững và phù hợp với nhu cầu phát triển. Tăng cường kiểm soát và quản lý đất đai bao gồm việc kiểm soát thực hiện quy hoạch, quản lý việc chuyển mục đích sử dụng đất.\n" +
//            "\n" +
//            "Cùng với đó, cần hoàn thiện cơ sở dữ liệu đất đai và tài nguyên môi trường. Trong phương án quy hoạch phát triển, TP.HCM cần gắn kết giữa quy hoạch phát triển cơ sở hạ tầng và hạ tầng kỹ thuật về bảo vệ môi trường.\n" +
//            "\n" +
//            "Ngoài ra, ngành tài nguyên và môi trường TP.HCM cần xây dựng triển khai kế hoạch hành động ứng phó với biến đổi khí hậu, kế hoạch thực hiện thỏa chung thuận Paris (đây là một thỏa thuận tại Hội nghị về biến đổi khí hậu của Liên Hiệp Quốc năm 2015) và kế hoạch xây dựng thành phố xanh, kinh tế xanh, kinh tế tuần hoàn.\n','https://cdn.tuoitre.vn/471584752817336320/2023/7/29/base64-1690605886696765648629.png',1)";
//    private String SQLQuery88 = "INSERT INTO tintuc VALUES (null,'Ngập cao tốc Phan Thiết - Dầu Giây: Do thiết kế có vấn đề hay thời tiết?','Ban quản lý dự án Thăng Long (Bộ Giao thông vận tải) đã trả lời thông tin liên quan đến vụ ngập cao tốc Phan Thiết - Dầu Giây vào sáng 29-7.\n" +
//
//            "Chủ đầu tư đánh giá lại nguyên nhân gây ngập\n" +
//            "\n" +
//            "Trả lời câu hỏi của Tuổi Trẻ Online về khâu thiết kế cốt nền, hệ thống thoát nước có lường trước được nước tràn gây ngập cao tốc Phan Thiết - Dầu Giây hay không? Đại diện Ban quản lý dự án Thăng Long (Bộ Giao thông vận tải) cho biết hiện phía tư vấn cũng đang đánh giá nguyên nhân và sẽ có báo cáo sau. \n" +
//            "\n" +
//            "Theo đánh giá ban đầu, nguyên nhân ngập vị trí nêu trên do khu vực tỉnh Bình Thuận rạng sáng nay mưa rất lớn, kèm theo đó là kênh mương khu vực ở phía hạ lưu thoát không kịp, tràn ra phạm vi đường cao tốc gây ngập cục bộ.\n" +
//            "Theo đại diện Ban quản lý dự án Thăng Long, tuyến chính cao tốc hiện đã được nghiệm thu và đã đưa vào khai thác tạm. Còn hệ thống đường gom trong quá trình khai thác địa phương đề nghị bổ sung thêm nên các nhà thầu đang thi công và chuẩn bị hoàn thiện.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/3641895009674940444559911448048486550370109n-16906079604811105472406.jpg',1)";
//    private String SQLQuery888 = "INSERT INTO tintuc VALUES (null,'Phương án mới giải quyết hàng ngàn tiểu thương đóng sai bảo hiểm xã hội','Bộ Lao động - Thương binh và Xã hội đã có thông tin mới về giải quyết vụ hàng ngàn tiểu thương đóng sai bảo hiểm xã hội bắt buộc khi sửa luật.\n" +
//
//            "Trong báo cáo giải trình, tiếp thu dự án Luật Bảo hiểm xã hội sửa đổi, Bộ Lao động - Thương binh và Xã hội cho biết có ý kiến đề nghị nghiên cứu, bổ sung quy định về giải quyết chế độ cho đối tượng chủ hộ kinh doanh đã đóng bảo hiểm xã hội bắt buộc trước khi dự luật này có hiệu lực để báo cáo Quốc hội xem xét, quyết định.\n" +
//            "\n" +
//            "Vấn đề này, Bộ Lao động - Thương binh và Xã hội cho hay liên quan đến đối tượng chủ hộ kinh doanh đã đóng bảo hiểm xã hội bắt buộc không đúng đối tượng thời gian qua, tại nghị quyết số 88 của Chính phủ đã giao các nhiệm vụ cụ thể.\n" +
//            "Trong đó, Bảo hiểm xã hội Việt Nam khẩn trương xây dựng báo cáo, cập nhật số liệu và đề xuất phương án giải quyết dứt điểm đối với tình trạng thu bảo hiểm xã hội không đúng quy định của pháp luật đối với chủ hộ kinh doanh.\n" +
//            "Do vậy, trên cơ sở báo cáo của Bảo hiểm xã hội Việt Nam với Chính phủ đã xác định trường hợp việc xử lý thuộc thẩm quyền của Quốc hội thì Chính phủ thống nhất phương án xử lý.\n" +
//            "Ngoài ra, thể hiện định hướng từng bước mở rộng diện bao phủ đối tượng tham gia bắt buộc đảm bảo tính khả thi trong tổ chức thực hiện.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/24/bao-hiem-xa-hoi19-1690189794105895791365.jpg',1)";
//    private String SQLQuery8888 = "INSERT INTO tintuc VALUES (null,'Công an xã thêm quyền, được quản lý thêm cơ sở kinh doanh nào?','Nghị định mới của Chính phủ bổ sung quyền cho công an xã chịu trách nhiệm cấp giấy chứng nhận đủ điều kiện về an ninh, trật tự và quản lý các cơ sở kinh doanh.\n" +
//
//            "Chính phủ đã ban hành nghị định số 56/2023 sửa đổi, bổ sung một số điều của nghị định 96/2016 quy định điều kiện về an ninh, trật tự đối với một số ngành, nghề đầu tư kinh doanh có điều kiện. Nghị định có hiệu lực từ ngày 15-8.\n" +
//            "\n" +
//            "Theo đó, nghị định đã bổ sung quy định công an xã chịu trách nhiệm cấp giấy chứng nhận đủ điều kiện về an ninh, trật tự và quản lý các cơ sở kinh doanh. Bao gồm cơ sở kinh doanh dịch vụ lưu trú có quy mô kinh doanh dưới 10 phòng, cơ sở kinh doanh khí là hộ kinh doanh.\n" +
//            "Công an cấp huyện chịu trách nhiệm cấp giấy chứng nhận với các đơn vị như cơ sở kinh doanh dịch vụ lưu trú có quy mô từ 10 - 20 phòng, cơ sở kinh doanh cung ứng dịch vụ sử dụng súng bắn sơn, kinh doanh dịch vụ karaoke...\n" +
//            "\n" +
//            "Cùng với đó, Cục Cảnh sát quản lý hành chính về trật tự xã hội (Bộ Công an) chịu trách nhiệm cấp giấy chứng nhận và quản lý các cơ sở kinh doanh, như kinh doanh súng quân dụng cầm tay hạng nhỏ, vật liệu nổ công nghiệp, kinh doanh tiền chất thuốc nổ; kinh doanh dịch vụ nổ mìn.\n" +
//            "\n" +
//            "Kinh doanh ngành, nghề có sử dụng vật liệu nổ công nghiệp để thăm dò, khai thác khoáng sản, dầu khí trên thềm lục địa Việt Nam, kinh doanh dịch vụ lưu trú được xếp hạng từ 5 sao trở lên, công cụ hỗ trợ...\n" +
//            "\n" +
//            "Giấy chứng nhận đủ điều kiện về an ninh, trật tự đã được cấp trước ngày 15-8 vẫn tiếp tục có giá trị sử dụng. Trường hợp cấp đổi, cấp lại sau ngày 15-8 sẽ thực hiện theo quy định tại nghị định.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/anh2-16906143532601478785503.jpeg',1)";
//    private String SQLQuery88888 = "INSERT INTO tintuc VALUES (null,'Hình ảnh thiệt hại ở Trung Quốc khi đón bão Doksuri mạnh nhất trong nhiều năm','Sau khi quét qua Philippines gây nhiều thương vong và sau đó là Đài Loan, bão Doksuri đổ bộ vào Trung Quốc gây gió to và ngập lụt.\n" +
//
//            "Hãng tin Reuters đưa tin ngày 29-7, bão Doksuri tiếp tục gây mưa lớn tại miền bắc Trung Quốc.\n" +
//            "\n" +
//            "Trước đó ngày 28-7, bão Doksuri đổ bộ vào Trung Quốc, là một trong những cơn bão mạnh nhất tấn công nước này trong nhiều năm nay.\n" +
//            "\n" +
//            "Bão đổ bộ đã làm sập đường dây điện và cây cối bật gốc, ảnh hưởng đến hơn 724.600 người và gây thiệt hại kinh tế trực tiếp hơn 7,3 triệu USD.\n" +
//            "\n" +
//            "Trước khi đến Trung Quốc, bão Doksuri đã quét qua miền bắc Philippines và Đài Loan, gây ra một vụ lật thuyền tại Philippines với ít nhất 26 người thiệt mạng. Hiện bão đang hướng về phía Bắc Kinh.\n" +
//            "Tàu điện tại thành phố Phúc Châu, tỉnh Phúc Kiến trong sáng 29-7 đã phải tạm dừng vì các ga tàu vẫn ngập nước.\n','https://cdn.tuoitre.vn/thumb_w/1060/471584752817336320/2023/7/29/2023-07-29t041650z-574533636-rc26c2ah5l77-rtrmadp-3-asia-weather-doksuri-china-16906179964142303047.jpg',1)";
//    private String SQLQuery001 = "INSERT INTO tintuc VALUES (null,' SpaceX phóng vệ tinh khổng lồ vào không gian','Tên lửa mạnh nhất của SpaceX cất cánh thành công vào trưa nay 29-7 (giờ Việt Nam), mang theo vệ tinh liên lạc thương mại lớn nhất thế giới. \n" +
//
//
//
//            "Chỉ một ngày trước đó, SpaceX đã phóng một tên lửa Falcon 9 từ bệ phóng khác của công ty ở bang Florida, mang theo một loạt vệ tinh Internet Starlink mới.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/1200x799-1-16906125490141034824234.jpg',1)";
//    private String SQLQuery002 = "INSERT INTO tintuc VALUES (null,' Trực thăng Úc lao xuống biển trong tập trận chung với Mỹ','Cuộc tập trận Talisman Sabre giữa Úc với Mỹ cùng nhiều nước khác đã bị tạm hoãn sau khi một trực thăng quân sự của Canberra rơi xuống biển tối 28-7.\n" +
//
//            "Cuộc tập trận bắt đầu ngày 21-7 và dự kiến kết thúc ngày 4-8. Theo Hãng tin Bloomberg, Úc đã hoãn tập trận sau vụ việc với chiếc MRH-90 ngày 28-7.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/truc-thang-uc-my-1690591814572878689217.jpg',1)";
//    private String SQLQuery003 = "INSERT INTO tintuc VALUES (null,'Người phụ nữ bị đâm chết khi đang đứng bán thịt','Đang đứng bán thịt, chị T. bị người đàn ông dùng dao đâm chết ngay trước sự chứng kiến của nhiều người.\n" +
//
//            "Do có mâu thuẫn từ trước, hai người xảy ra cự cãi rồi Sơn bất ngờ dùng dao cắt thịt đâm vào cổ chị T..\n" +
//            "\n" +
//            "Sau khi chị T. ngã xuống, Sơn còn dùng dao tiếp tục cứa vào cổ chị T. khiến chị tử vong sau đó.\n" +
//            "\n" +
//            "Sự việc xảy ra trước sự chứng kiến của nhiều người dân. Ngay khi được trình báo, công an địa phương đã có mặt kịp thời để tạm giữ Sơn.\n.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/28/ba-ban-thit-bi-dam-chet-16905486503481061296357.jpg',1)";
//    private String SQLQuery004 = "INSERT INTO tintuc VALUES (null,'Công ty môi trường bị xử phạt vì xả thải vượt quy chuẩn','Xả thải vượt quy chuẩn, một công ty môi trường đã bị xử phạt gần 1 tỉ đồng và đình chỉ 2 lò đốt chất thải rắn sinh hoạt trong thời hạn 4,5 tháng.\n" +
//
//            "Công ty cổ phần Môi trường Thái Nguyên đã bị xử phạt với tổng số tiền là 954.700.000 đồng và đình chỉ hoạt động thải bụi, khí thải gây ô nhiễm môi trường phát sinh từ 2 lò đốt chất thải rắn sinh hoạt trong thời hạn 4,5 tháng.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/28/base64-16905373756201975795774.png',1)";
//    private String SQLQuery005 = "INSERT INTO tintuc VALUES (null,'Bất ngờ với nhiều cổ phiếu tăng giá tới 20% chỉ trong một tuần','Giới đầu tư không khỏi vui mừng khi chứng kiến chỉ số chứng khoán VN-Index vượt mốc 1.200 điểm, dòng tiền ào ạt đổ vào thị trường, cả nhà đầu tư nước ngoài cũng liên tục mua ròng. Nhiều cổ phiếu tăng nóng. \n" +
//
//            "Dòng tiền ào ạt được nhà đầu tư trong nước đổ vào, hợp lực cùng nhà đầu tư ngoại. Tổng giá trị mua bán cổ phiếu trên sàn tăng mạnh. Thanh khoản bình quân ba sàn trong tuần này đạt hơn 23.500 tỉ đồng, tăng gần 16% so với tuần trước.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/base64-16906050286661369891535.png',1)";
//    private String SQLQuery006 = "INSERT INTO tintuc VALUES (null,'Cuộc chiến Coteccons và Ricons: Hai bên cùng hẹn nhau... tại tòa','Cả Coteccons và Ricons đều khẳng định sẽ xử lý dứt điểm những công nợ và tranh chấp giữa hai bên tại tòa án.\n" +
//
//            "Doanh nghiệp này cho rằng sẵn sàng hợp tác với một tinh thần thiện chí, sẵn sàng cung cấp đầy đủ theo những yêu cầu của tòa án để sớm xử lý dứt điểm những công nợ, đề cao đạo đức trong kinh doanh doanh nghiệp.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/dji0174-1690607040665543888072.jpg',1)";
//    private String SQLQuery007 = "INSERT INTO tintuc VALUES (null,'Sóc Trăng: Định hướng đầu tư cảng biển Trần Đề','Ông Trần Văn Lâu - chủ tịch UBND tỉnh Sóc Trăng - cho biết vào ngày 7-8 tới đây, Sóc Trăng sẽ phối hợp Bộ Giao thông vận tải tổ chức hội thảo về đầu tư cảng biển Trần Đề.\n" +
//
//            "Nhiều ưu đãi cho nhà đầu tư\n" +
//            "Theo ông Lâu, hội thảo sẽ tập trung thảo luận cho thấy sự cần thiết đầu tư cảng nước sâu tại Trần Đề và vai trò của cảng trong hệ thống logistics quốc tế. Dịp này Sóc Trăng cũng muốn lắng nghe các nhà đầu tư đề xuất cơ chế, vốn để đầu tư vào cảng. Hiện Sóc Trăng đang tích cực khẩn trương thực hiện các thủ tục cần thiết để triển khai dự án này.\n" +
//            "\n" +
//            "Theo người đứng đầu chính quyền Sóc Trăng, khi đầu tư vào dự án, nhà đầu tư sẽ được hưởng nhiều chính sách ưu đãi như áp dụng thuế suất ưu đãi 10% trong 15 năm; miễn thuế 4 năm, giảm 50% số thuế phải nộp trong 9 năm tiếp theo và được miễn tiền thuê đất, thuê mặt nước cho cả thời gian thuê; miễn thuế đối với hàng hóa nhập khẩu để tạo tài sản cố định.\n" +
//            "\n" +
//            "Nhà đầu tư cũng được miễn thuế trong thời hạn 5 năm kể từ khi bắt đầu sản xuất, đối với nguyên liệu, vật tư, linh kiện trong nước chưa sản xuất được nhập khẩu để sản xuất.\n" +
//            "\n" +
//            "Ông Lâu cho biết mới đây, Thủ tướng đã có quyết định 886 phê duyệt kế hoạch, chính sách, giải pháp và nguồn lực thực hiện quy hoạch tổng thể phát triển hệ thống cảng biển Việt Nam thời kỳ 2021 - 2030 và tầm nhìn đến năm 2050.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/cangtrande1-16906203184041999976757.jpg',1)";
//    private String SQLQuery008 = "INSERT INTO tintuc VALUES (null,'Giá nhà Singapore vẫn tăng bất chấp tình hình thế giới','Giá nhà ở tại Singapore vẫn tăng 3,1% trong nửa đầu năm 2023, đi ngược xu hướng giảm khắp thế giới.\n" +
//
//            "Theo Hội đồng Môi giới bất động sản Singapore, số đăng ký tham gia thi sát hạch để trở thành môi giới đã tăng gấp đôi, lên đến 20.000 người trong năm 2022, so với năm 2019.\n" +
//            "\n" +
//            "Dù giá bất động sản tăng cao, các nhà phân tích không mong đợi chính phủ sẽ đưa ra những biện pháp khắc phục khác, đặc biệt trong bối cảnh lãi suất tăng làm hạn chế bớt nhu cầu.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/28/singaporealestate-16905417649391231005743.png',1)";
//    private String SQLQuery009 = "INSERT INTO tintuc VALUES (null,'Đơn hàng ngành gỗ nội thất tăng trở lại',' Chiều 28-7, tại Diễn đàn công nghiệp gỗ và nội thất Việt Nam, ông Nguyễn Quốc Khanh, chủ tịch Hội Mỹ nghệ - Chế biến gỗ TP.HCM, cho biết từ cuối quý 2-2023 các đơn hàng ngành gỗ đã bắt đầu trở lại dù chưa nhiều.\n" +
//
//            "Tuy vậy, ngành nội thất Việt Nam vẫn có khả năng hoàn thành chỉ tiêu xuất khẩu đề ra và cần một chiến lược mới để có thể chinh phục những mục tiêu bền vững hơn.\n" +
//            "\n" +
//            "Chia sẻ cụ thể về tín hiệu hồi phục từ top 5 thị trường xuất khẩu của đồ gỗ Việt Nam như Mỹ, châu Âu, Nhật Bản..., bà Trần Như Trang, đại diện chương trình SIPPO Việt Nam, cho biết không chỉ tăng nhẹ trở lại, các thị trường này cũng xuất hiện nhiều xu hướng và yêu cầu mới.\n" +
//            "\n" +
//            "Chẳng hạn thị trường châu Âu - nơi dẫn dắt xu hướng tiêu dùng chính của nhóm hàng này khi chiếm 2/3 thị phần các mặt hàng cao cấp nhập từ Việt Nam - đặt ra nhiều yêu cầu về tiêu dùng bền vững. Các sản phẩm được yêu cầu phải có \"hộ chiếu số\", cung cấp đủ thông tin về nguyên liệu thân thiện môi trường, quy trình sản xuất...\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/28/cong-nhan-san-xuat-go-dafi-2-4read-only-16905584503491569753746.jpg',1)";
//    private String SQLQuery0010 = "INSERT INTO tintuc VALUES (null,'Giải pháp an ninh mạng Make in Vietnam ra thị trường toàn cầu','Giải pháp an ninh mạng SafeGate hoàn toàn do các chuyên gia công nghệ, kỹ sư Việt Nam nghiên cứu phát triển đã đạt được thỏa thuận ra thị trường quốc tế.\n" +
//
//            "Trước đó, giải pháp an ninh mạng Make in Vietnam SafeGate cũng đã được Bộ trưởng Bộ Thông tin và Truyền thông Nguyễn Mạnh Hùng đánh giá cao, đồng thời đề xuất đưa vào triển khai rộng ở các trường học trong cả nước để xây dựng môi trường Internet an toàn trong trường học.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/28/an-ninh-mang-16905382814051679827919.jpg',1)";
//    private String SQLQuery0011 = "INSERT INTO tintuc VALUES (null,' Gần 800 tỉ đồng tài trợ phi lợi nhuận cho các nhà khoa học Việt Nam','Giáo sư Vũ Hà Văn cho biết sau 5 năm hoạt động, Quỹ VINIF đã tiếp sức cho 2.500 nhà khoa học Việt Nam với tổng kinh phí gần 800 tỉ đồng\n" +
//
//            "Phát biểu tại hội thảo Dấu ấn 5 năm hoạt động tổng kết hành trình đồng hành thúc đẩy nghiên cứu khoa học Việt Nam do Quỹ Đổi mới sáng tạo Vingroup (VINIF) tổ chức tại Hà Nội sáng 26-7, GS Vũ Hà Văn, giám đốc khoa học Quỹ VINIF, cho biết: 5 năm trước, trong bối cảnh ngành khoa học công nghệ Việt Nam cần một bước ngoặt lớn về chất và lượng, Quỹ VINIF được thành lập với mục tiêu hỗ trợ các nhà khoa học trẻ thuộc các trường đại học, học viện thực hiện nghiên cứu khoa học công nghệ và đổi mới sáng tạo.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/26/35970458626691591832242893547001044187114405n-16903591312011598123640.jpg',1)";
//    private String SQLQuery0012 = "INSERT INTO tintuc VALUES (null,' Thí sinh có thể nộp lệ phí xét tuyển đại học 2023 bằng mã QR',' Thí sinh, phụ huynh có thể thanh toán lệ phí xét tuyển đại học 2023 bằng cách quét mã QR và xác nhận là hoàn tất nhanh chóng, dễ dàng, chính xác mà không cần nhập tay số tài khoản, mật khẩu, mã kiểm tra và mã OTP.\n" +
//
//            "Việc áp dụng hình thức quét mã QR để thanh toán lệ phí xét tuyển đại học 2023 giúp cho các thí sinh, phụ huynh từ các vùng quê không rành công nghệ hoặc lần đầu thanh toán qua nền tảng trực tuyến cũng dễ dàng thực hiện.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/25/nop-le-phi-xet-tuyen-dai-hoc-2023-bang-ma-qr-vi-dien-tu-momo-16902718780711585773776.jpg',1)";
//    private String SQLQuery0014 = "INSERT INTO tintuc VALUES (null,'Meta bị phạt 14 triệu USD do thu thập dữ liệu người dùng trái phép',' Ngày 26-7, Tòa án liên bang Úc đã phạt Meta - công ty mẹ của Facebook - 20 triệu AUD (14 triệu USD) do thu thập trái phép dữ liệu người dùng qua một ứng dụng điện thoại thông minh.\n" +
//
//            "Tuy nhiên Onavo đã thu thập trái phép vị trí, thời gian, tần suất đăng nhập các ứng dụng điện thoại thông minh và các trang web người dùng truy cập để sử dụng phục vụ mục đích thương mại riêng.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/26/meta-reuters-16903528678411147330711.png',1)";
//    private String SQLQuery0015 = "INSERT INTO tintuc VALUES (null,'Cảnh báo trào lưu mới rất nguy hiểm trên TikTok: Uống hàn the','Các quan chức y tế Mỹ cảnh báo trào lưu TikTok mới nhất là thêm hàn the vào nước uống để giảm viêm và giảm đau khớp rất nguy hiểm và có thể gây chết người\n" +
//
//            "Theo Hãng tin UPI, cũng giống như nhiều trào lưu trực tuyến, không ai biết việc phổ biến uống hàn the để giảm viêm và giảm đau khớp bắt đầu như thế nào, nhưng hiện nay nó trở thành xu hướng được bàn luận nhiều trên TikTok.\n" +
//            "\n" +
//            "Bác sĩ Kelly Johnson - Arbor nhấn mạnh với Đài NBC News: Thực sự không có gì chứng minh việc sử dụng hàn the giúp giảm viêm hoặc giảm stress hoặc bất cứ điều gì tương tự.\n" +
//            "\n" +
//            "Tài khoản @chem.thug, người tự nhận mình là một nhà hóa học đang làm luận án tiến sĩ về hóa học hữu cơ, cũng đăng video giải thích tại sao ăn hàn the là một ý tưởng tồi. Anh gọi nó là nguy hiểm rõ ràng. Video hiện lan truyền nhanh chóng, với hơn 1,8 triệu lượt xem.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/24/experts-warn-that-tik-tok-trend-of-eating-borax-is-highly-dangerous-169019917331979724906.jpg',1)";
//
//    private String SQLQuery0016 = "INSERT INTO tintuc VALUES (null,'Phạt cửa hàng bán SIM nhập sẵn thông tin thuê bao',' Kiểm tra cửa hàng Mỹ Hạnh, cơ quan chức năng phát hiện có 109 SIM đã kích hoạt nhập sẵn thông tin thuê bao của các nhà mạng VNPT, Viettel, MobiFone, Wintel\n" +
//
//            "Cụ thể, xử phạt 17,5 triệu đồng đối với Lê Thị Thu Ba (sinh năm 1979, trú xã Phú Hội, huyện Đức Trọng) do bày bán 5 SIM VinaPhone nhưng không được doanh nghiệp viễn thông ký hợp đồng ủy quyền giao kết hợp đồng theo mẫu, điều kiện giao dịch chung. \n" +
//            "\n" +
//            "Đồng thời xử phạt 17,5 triệu đồng đối với Nguyễn Thị Mỹ Hạnh (sinh năm 1984, trú xã Phú Hội, huyện Đức Trọng) do lấy nguồn SIM đã nhập sẵn thông tin thuê bao từ tỉnh Hải Dương về bán tại cửa hàng Mỹ Hạnh.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/24/sim-di-dong-anh-istock-1690167741911919180473.jpg',1)";
//    private String SQLQuery0017 = "INSERT INTO tintuc VALUES (null,'Nga bắt đầu cấp thị thực điện tử cho Việt Nam từ tháng 8','Bên cạnh 52 quốc gia thân thiện với Nga, công dân Việt Nam, Campuchia và Myanmar cũng sẽ được cấp thị thực điện tử E-visa để du lịch và làm việc tại Nga \n" +
//
//            "Ngoài ra, tờ Izvestia dẫn lời Phó thủ tướng Nga Dmitry Chernyshenko cho biết thêm Trung Quốc và Ấn Độ có thể sẽ là hai quốc gia đầu tiên áp dụng hệ thống thị thực điện tử để nhập cảnh vào Nga.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/e-visa-1690602314543554096342.jpg',1)";
//
//    private String SQLQuery0019 = "INSERT INTO tintuc VALUES (null,'Quý 2-2024 có phà biển Cần Giờ - Tiền Giang',' Sau khi hoàn thành việc lựa chọn doanh nghiệp, Sở Giao thông vận tải TP.HCM sẽ triển khai khai thác tuyến phà biển Cần Giờ - Tiền Giang trong quý 2-2024\n" +
//
//            "Sở Giao thông vận tải TP.HCM vừa có văn bản xin ý kiến UBND TP về việc chấp thuận phương án khai thác, chủ trương xây dựng tiêu chí và thành lập tổ công tác lựa chọn doanh nghiệp khai thác hoạt động tuyến phà nối TP.HCM với tỉnh Tiền Giang.\n" +
//            "\n" +
//            "Theo kế hoạch, tuyến phà này có chiều dài khoảng 12km, từ xã Long Hòa, huyện Cần Giờ đi thị trấn Vàm Láng, huyện Gò Công Đông, tỉnh Tiền Giang và ngược lại. Kinh phí đầu tư ước tính khoảng 114 tỉ đồng. Dự kiến được đầu tư theo hình thức xã hội hóa. \n" +
//            "\n" +
//            "Sở Giao thông vận tải TP.HCM đề xuất xây dựng tiêu chí để lựa chọn doanh nghiệp có năng lực, bảo đảm tính công khai, minh bạch, không lập dự án đầu tư và sử dụng nguồn vốn ngân sách nhà nước.\n','https://cdn.tuoitre.vn/471584752817336320/2023/7/28/9db94801bf3c6c62352d-1690549668391720563713.jpg',1)";
//    private String SQLQuery0020 = "INSERT INTO tintuc VALUES (null,'Cù lao Câu ở Bình Thuận nhận bằng xếp hạng di tích cấp tỉnh','Phó chủ tịch UBND tỉnh Bình Thuận Nguyễn Minh vừa trao bằng xếp hạng di tích cấp tỉnh đối với di tích thắng cảnh Hòn Cau (Cù lao Câu) cho huyện Tuy Phong\n" +
//
//            "Thắng cảnh Hòn Cau chứa đựng nhiều giá trị về lịch sử hình thành địa chất, địa mạo mang đặc trưng của vùng duyên hải Nam Trung Bộ trong hàng triệu năm qua.\n" +
//            "\n" +
//            "Đây còn là điểm đến có cảnh quan thiên nhiên độc đáo, hấp dẫn và kỳ bí về quá trình hình thành địa chất, địa mạo, hệ sinh thái đa dạng, nhiều chủng loại.\n" +
//            "\n" +
//            "Hòn Cau còn được coi là điểm nhấn trên bản đồ du lịch Việt Nam, đang có sức thu hút du khách khắp nơi tìm đến khám phá, trải nghiệm.\n" +
//            "\n" +
//            "Ngoài hệ sinh thái biển phong phú, nơi đây còn được thiên nhiên ban tặng cho một \"thế giới đá\" với những khối có nhiều hình hài, màu sắc, kích cỡ chồng xếp lên nhau trải dài nối tiếp, xen kẽ với những bãi cát trắng mịn bao quanh chân đảo.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/28/base64-1690517974273898613507.png',1)";
//    private String SQLQuery0021 = "INSERT INTO tintuc VALUES (null,'Mỹ Tâm, Đức Phúc khai mạc lễ hội Tận hưởng mùa hè Đà Nẵng','Đêm 28-7, Sở Du lịch TP Đà Nẵng tổ chức khai mạc lễ hội Tận hưởng mùa hè Đà Nẵng 2023 tại công viên Biển Đông với sự xuất hiện của Mỹ Tâm, Đức Phúc, Only C.\n" +
//
//
//            "Ông Hồ Kỳ Minh - phó chủ tịch thường trực UBND TP Đà Nẵng, cho biết việc tổ chức lễ hội lần này nhằm kích cầu du lịch thành phố mùa cao điểm hè, giới thiệu tới công chúng trong và ngoài nước điểm đến Đà Nẵng với các sản phẩm du lịch đa dạng, hấp dẫn','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/base64-1690567386806558266587.png',1)";
//    private String SQLQuery0022 = "INSERT INTO tintuc VALUES (null,'Việt Nam giành suất play-off nhóm II thế giới Davis Cup 2024','Ngày 29-7, tuyển quần vợt Việt Nam đã thắng Jordan 2-1 ở trận tranh suất play-off nhóm II thế giới Davis Cup 2024.\n" +
//
//            "Ngày 29-7, tuyển Việt Nam đã thi đấu trận tranh suất play-off nhóm II thế giới Davis Cup 2024 với Jordan (đội đứng thứ 2 tại bảng B). Đây cũng là chiếc vé cuối cùng của khu vực châu Á - Thái Bình Dương bên cạnh Iran (nhất bảng A) và Pacific Oceania (nhất bảng B) tham dự play-off nhóm II thế giới Davis Cup 2024.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/tuyen-quan-vot-viet-nam-1690621534896531446121.jpg',1)";
//    private String SQLQuery0023 = "INSERT INTO tintuc VALUES (null,'Tuyển U23 Việt Nam sẽ đấu giao hữu với U23 Bahrain','Sáng 29-7, tuyển U23 Việt Nam đã tập luyện buổi đầu tiên nhằm chuẩn bị cho Giải U23 Đông Nam Á 2023 tại Thái Lan sắp tới.\n" +
//
//            "Mặc dù chưa có đầy đủ lực lượng trong tay, nhưng HLV Hoàng Anh Tuấn vẫn triển khai giáo án tập luyện rất kỹ lưỡng và bài bản. \n" +
//            "\n" +
//            "Đây là điều rất quan trọng trong giai đoạn chuẩn bị đầu tiên của đội. Bởi đa số các cầu thủ lên tập trung đều là những nhân tố mới. Trong đó, nhiều cầu thủ còn chưa đầy 18 tuổi.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/hlv-hoang-anh-tuan-chi-dao-u23-viet-nam-1690609952998710798791.jpg',1)";
//    private String SQLQuery0024 = "INSERT INTO tintuc VALUES (null,'Xếp hạng bảng D World Cup nữ 2023: Anh nhất bảng, Trung Quốc đứng thứ ba','Tuyển nữ Anh độc chiếm ngôi vị số 1 bảng D World Cup nữ 2023 sau hai trận toàn thắng. Đan Mạch và Trung Quốc chia nhau 2 vị trí tiếp theo.\n" +
//
//
//            "Lượt trận thứ 2 bảng D World Cup nữ 2023 khép lại với các kết quả: tuyển nữ Anh thắng Đan Mạch 1-0 và Trung Quốc vượt qua Haiti với tỉ số tương tự. \n" +
//            "\n" +
//            "Như vậy, sau hai lượt đấu, tuyển nữ Anh độc chiếm ngôi đầu bảng D với 6 điểm. Đan Mạch và Trung Quốc chia nhau 2 vị trí tiếp theo với cùng 3 điểm. Đứng cuối bảng là Haiti, chưa có điểm nào.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/3573204261160447874911214326725022040236808n-16905831455391705987803.jpeg',1)";
//    private String SQLQuery0025 = "INSERT INTO tintuc VALUES (null,'CLB Hà Nội tạm vươn lên dẫn đầu V-League','Tối 28-7, tại vòng 3 giai đoạn 2 V-League 2023, CLB Hà Nội đã có chiến thắng 1-0 khi tiếp Nam Định trên sân nhà.\n" +
//
//            "Cho tới lúc này, CLB Hà Nội vẫn đang cạnh tranh chức vô địch cùng Công An Hà Nội, Thanh Hóa và Viettel. Do đó, họ cần có được 3 điểm trước Nam Định để duy trì cuộc đua. \n" +
//            "\n" +
//            "CLB Hà Nội cũng có lợi thế khi Văn Quyết có trận thứ 2 trở lại sau án cấm thi đấu 8 trận.\n" +
//            "\n" +
//            "Dù vậy, cũng như các trận đấu gần đây, CLB Hà Nội lại có khởi đầu chậm chạp. Họ để gần như thế trận về phía Nam Định. Các đợt lên bóng đáng chú ý đầu trận gần như thuộc về đội khách.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/28/clb-ha-noi-nam-dinh-v-league-2023-1690554423621251076927.jpg',1)";
//    private String SQLQuery0026 = "INSERT INTO tintuc VALUES (null,'Ký túc xá Đại học Quốc gia TP.HCM miễn, giảm phí nội trú cho sinh viên khó khăn','Năm học 2023 - 2024, ký túc xá Đại học Quốc gia TP.HCM triển khai chính sách miễn, giảm phí nội trú cho sinh viên thuộc các đối tượng như khuyết tật, sinh viên mồ côi, khó khăn... \"Bếp ăn chia sẻ\" cũng được triển khai tại ký túc xá\n" +
//
//            "Từ ngày 1 đến 10-8, Trung tâm quản lý ký túc xá Đại học Quốc gia TP.HCM sẽ mở cổng tiếp nhận đăng ký ở ký túc xá năm học 2023-2024 đối với sinh viên đang nội trú năm học 2022-2023 và sinh viên từ năm thứ 2 trở lên thuộc các trường thành viên trực thuộc Đại học Quốc gia TP.HCM có nhu cầu ở.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/photo-1690592575604-169059257670718450272.jpg',1)";
//    private String SQLQuery0027 = "INSERT INTO tintuc VALUES (null,'Nghịch lý tuyển sinh lớp 10','Trong khi nhiều học sinh ở nội thành TP.HCM rớt cả ba nguyện vọng vào lớp 10 công lập, thì ở ngoại thành và vùng ven, nhiều trường THPT tuyển sinh không đủ chỉ tiêu\n" +
//
//
//            "Kỳ thi tuyển sinh lớp 10 ở TP.HCM năm 2023 được xem là kỳ thi căng thẳng hơn cả thi đại học vì các trường THPT công lập chỉ lấy 70% học sinh vào học lớp 10 trong tổng số 109.000 học sinh lớp 9 trên địa bàn. Tính cả số thí sinh bỏ thi và số học sinh không đăng ký dự thi tuyển sinh lớp 10, năm nay vẫn có gần 20.000 học sinh ở TP.HCM rớt lớp 10 công lập.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/lop-10-1690593281367883646826.jpg',1)";
//    private String SQLQuery0028 = "INSERT INTO tintuc VALUES (null,'Không thể để vé số dạo tràn xuống lòng đường','Các đại lý từ chối nhận vé số thừa khiến nhiều người bán vé số dạo phải tràn xuống lòng đường mong bán kịp hết vé trước giờ xổ số, gây nguy cơ mất an toàn giao thông. \n" +
//
//            "Công ty xổ số cần xử lý dứt dạt các đại lý, không được o ép người bán vé số dạo, và cần thêm nhiều biện pháp mới chấm dứt chuyện biết nguy hiểm nhưng vẫn chặc lưỡi mưu sinh.\n" +
//            "\n" +
//            "Bạn đọc Xứ Dừa cho rằng việc các công ty xổ số sẽ cắt hợp đồng nếu đại lý không nhận lại vé số ế là vô cùng cần thiết. Nhưng quan trọng là các đại lý có thực hiện hay vẫn làm luật ngầm với người bán vé số dạo. Vì thế các vấn đề liên quan về quyền lợi, nghĩa vụ của các bên cần được quy định rõ ràng bằng văn bản pháp luật.\n','https://cdn.tuoitre.vn/471584752817336320/2023/7/28/base64-16905076717042118192699.png',1)";









    public database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
//    private String SQLQuery10= "INSERT INTO tintuc VALUES (null,'Chấm dứt hợp đồng với đại lý không nhận vé số ế','Các công ty xổ số thành viên cho biết nếu phát hiện đại lý còn ép không cho người bán dạo trả lại vé số ế, thì sẽ thanh lý hợp đồng\n" +
//
//            "ÝÔng Lê Văn Khanh - chủ tịch Công ty TNHH một thành viên Xổ số kiến thiết tỉnh Sóc Trăng - nhấn mạnh nếu phát hiện đại lý nào còn ép không cho người bán dạo trả lại vé số ế và hoa hồng tối thiểu của người bán vé số lẻ dưới 10%, sẽ xử lý ngay.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/27/base64-16904754416711623261459.png',1)";
//    private String SQLQuery00= "INSERT INTO tintuc VALUES (null,'Cảnh báo trang web giả mạo Bảo hiểm xã hội Việt Nam','Trang web dùng IP nước ngoài có tên miền, giao diện giả mạo Bảo hiểm xã hội Việt Nam để lừa đảo người dùng.\n" +
//
//            "Ngày 25-7, Bảo hiểm xã hội Việt Nam phát đi cảnh báo trang web giả mạo Cổng dịch vụ công ngành bảo hiểm xã hội. Theo đó, cơ quan này phát hiện trang web có tên miền dichvucongbaohiemxahoi.com.\n" +
//            "\n" +
//            "Trang web này có tên miền, giao diện rất giống Cổng dịch vụ công của Bảo hiểm xã hội Việt Nam. Ngoài ra, trang web dichvucongbaohiemxahoi.com còn giả mạo đặc điểm nhận diện như logo, hình ảnh minh họa, chức năng như trang web thật của ngành bảo hiểm.\n','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/25/h1-1690273496595372465722.jpg',1)";
//    private String SQLQuery01= "INSERT INTO tintuc VALUES (null,'Có chú chuột dừa biết đi bán vé số ở Bến Tre','Người sành ăn coi chuột dừa là đặc sản Bến Tre, nhưng một phụ nữ xứ này lại xem chú chuột dừa là người bạn thân thiết trong hành trình mưu sinh của mình... chuột dừa biết đi bán vé số ở Bến Tre\n" +
//
//            "Đến huyện Mỏ Cày Nam, hầu như ai cũng biết chuyện chú chuột Lắc lanh lợi đến kỳ lạ, khó tin cùng một phụ nữ rong ruổi bán vé số.','https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/7/29/base64-169058305005084762635.png',1)";
//    private String SQLQuery02= "INSERT INTO tintuc VALUES (null,'Mưa to trước thềm đêm diễn của Blackpink tại Hà Nội','Cơn mưa nặng hạt đổ xuống sân vận động quốc gia Mỹ Đình – Hà Nội trước giờ đêm nhạc của nhóm Blackpink diễn ra. Người hâm mộ mặc áo mưa, tìm nơi trú ẩn với mong mỏi sớm trời quang mây tạnh.\n" +
//
//            "Sau cái nắng gay gắt, cơn mưa khiến nhiều người phải rời khỏi ghế ngồi trong sân vận động, tìm nơi trú ẩn. Trước đó, ban tổ chức đã mở cổng để khán giả tham dự đêm nhạc có thể tiến vào dần dần.\n" +
//            "\n" +
//            "Đêm nhạc của nhóm Blackpink diễn ra ngày 29 và 30-7. Trước đó, việc Hà Nội có thể có mưa cũng đã được dự báo.\n" +
//            "\n" +
//            "Dẫu phải vất vả vì mưa, người hâm mộ nhóm nhạc vẫn thể hiện tinh thần \"chiến hết mình\" vì nhóm nhạc thân yêu.','https://nld.mediacdn.vn/thumb_w/684/291774122806476800/2023/7/29/f2mxgdsa4aa7evv-16906302196881725467196.jpg',1)";
//    private String SQLQuery03= "INSERT INTO tintuc VALUES (null,'Sẽ đổi, cấp lại Giấy phép lái xe theo phân hạng mới?','Cụ thể, Giấy phép lái xe đã được cấp theo Luật Giao thông đường bộ năm 2008 được tiếp tục sử dụng, đối với các trường hợp quy định tại khoản 2 (Giấy phép lái xe được cấp lại), 3 (Giấy phép lái xe được đổi) Điều 43 của Luật này thì được đổi, cấp lại theo phân hạng mới.\n" +
//
//            "Giấy phép lái xe hạng A1, A2, A3, B1, B2, C, D, E, FB2, FC, FD, FE được cấp theo Luật Giao thông đường bộ 2008 được đổi, cấp lại như sau:\n" +
//            "\n" +
//            "a) Giấy phép lái xe hạng A3, C giữ nguyên và đổi, cấp lại cùng hạng;\n" +
//            "\n" +
//            "b) Giấy phép lái xe hạng A2 đổi, cấp lại cho những người có giấy phép lái xe hạng A1;\n" +
//            "\n" +
//            "c) Giấy phép lái xe hạng A đổi, cấp lại cho những người có giấy phép lái xe hạng A2;\n" +
//            "\n" +
//            "d) Giấy phép lái xe hạng B đổi, cấp lại cho những người có giấy phép lái xe hạng B1, B2;\n" +
//            "\n" +
//            "đ) Giấy phép lái xe hạng D2 đổi, cấp lại cho những người có giấy phép lái xe hạng D;\n" +
//            "\n" +
//            "e) Giấy phép lái xe hạng D đổi, cấp lại cho những người có giấy phép lái xe hạng E;\n" +
//            "\n" +
//            "g) Giấy phép lái xe hạng BE đổi, cấp lại cho những người có giấy phép lái xe hạng FB2;\n" +
//            "\n" +
//            "h) Giấy phép lái xe hạng CE đổi, cấp lại cho những người có giấy phép lái xe FC;\n" +
//            "\n" +
//            "i) Giấy phép lái xe hạng D2E đổi, cấp lại cho những người có giấy phép lái xe hạng FD;\n" +
//            "\n" +
//            "k) Giấy phép lái xe hạng DE đổi, cấp lại cho những người có giấy phép lái xe hạng FE.','https://xdcs.cdnchinhphu.vn/thumb_w/900/446259493575335936/2023/7/10/20161106092023-y-nghia-cua-giay-phep-lai-xe-hang-a-b-c-d-e-f-1688972857281566098033-0-81-560-977-crop-16889728705561348588763.jpg',1)";
//    private String SQLQuery04= "INSERT INTO tintuc VALUES (null,'Chùa Ba Vàng: Thu tiền công đức hơn 4,16 tỷ đồng, chi hết cho từ thiện','Báo cáo thu chi của Chùa Ba Vàng nêu rõ từ ngày 19/3-30/4/2023, nhà chùa thu công đức, tài trợ là 4.164.500.000 đồng. Toàn bộ số tiền này đã được chi hết cho hoạt động từ thiện, nhân đạo.\n" +
//
//            "Báo cáo cũng lập luận, theo quy định của Bộ Tài chính khi thí điểm kiểm tra việc quản lý tiền công đức tại các di tích lịch sử-văn hóa, đình chùa trên địa bàn tỉnh Quảng Ninh, thì nội dung kiểm tra chỉ là việc tiếp nhận, quản lý và sử dụng tiền công đức, tài trợ cho di tích và hoạt động lễ hội.\n" +
//            "\n" +
//            "Từ đó, đại diện Chùa Ba Vàng cho biết chùa thực hiện báo cáo việc quản lý thu chi tiền công đức, tài trợ cho di tích và hoạt động lễ hội.\n" +
//            "\n" +
//            "Còn tiền công đức, tài trợ cho hoạt động tôn giáo là vấn đề nội bộ của Giáo hội Phật giáo Việt Nam, được quản lý theo quy định của giáo hội, phù hợp với Giáo luật của Đức Phật và pháp luật của Nhà nước.','https://cdnimg.vietnamplus.vn/t620/uploaded/ngtmbh/2023_07_29/ttxvn_chua_ba_vang.jpg',1)";

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
//        db.execSQL(SQLQuery3);
        db.execSQL(SQLQuery4);
        db.execSQL(SQLQuery5);
//        db.execSQL(SQLQuery5);
//        db.execSQL(SQLQuery6);
//        db.execSQL(SQLQuery7);
//        db.execSQL(SQLQuery8);
//        db.execSQL(SQLQuery88);
//        db.execSQL(SQLQuery888);
//        db.execSQL(SQLQuery8888);
//        db.execSQL(SQLQuery88888);
//        db.execSQL(SQLQuery001);
//        db.execSQL(SQLQuery002);
//        db.execSQL(SQLQuery003);
//        db.execSQL(SQLQuery004);
//        db.execSQL(SQLQuery005);
//        db.execSQL(SQLQuery006);
//        db.execSQL(SQLQuery007);
//        db.execSQL(SQLQuery008);
//        db.execSQL(SQLQuery009);
//        db.execSQL(SQLQuery0010);
//        db.execSQL(SQLQuery0011);
//        db.execSQL(SQLQuery0012);
//        db.execSQL(SQLQuery0014);
//        db.execSQL(SQLQuery0015);
//        db.execSQL(SQLQuery0016);
//        db.execSQL(SQLQuery0017);
//        db.execSQL(SQLQuery0019);
//        db.execSQL(SQLQuery0020);
//        db.execSQL(SQLQuery0021);
//        db.execSQL(SQLQuery0022);
//        db.execSQL(SQLQuery0023);
//        db.execSQL(SQLQuery0024);
//        db.execSQL(SQLQuery0025);
//        db.execSQL(SQLQuery0026);
//        db.execSQL(SQLQuery0027);
//        db.execSQL(SQLQuery0028);
//        db.execSQL(SQLQuery11);
//        db.execSQL(SQLQuery12);
        db.execSQL(SQLQuery13);
//        db.execSQL(SQLQuery10);
//        db.execSQL(SQLQuery02);
//        db.execSQL(SQLQuery00);
//        db.execSQL(SQLQuery01);
//        db.execSQL(SQLQuery03);
//        db.execSQL(SQLQuery04);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void AddTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN,taiKhoan.getmTenTaiKhoan());
        values.put(MAT_KHAU,taiKhoan.getmMatKhau());
        values.put(EMAIL,taiKhoan.getmEmail());
        values.put(PHAN_QUYEN,taiKhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN,null,values);
        //đóng lại db cho an toàn
        db.close();
        //Log.e("Add Tai Khoan ","thành công");
    }


    //Lấy tất cả tài khoản
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_TAIKHOAN , null );
        return res;
    }



    //Thêm công thức
    public void AddTruyen(Tin tin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_TINTUC, tin.getTenTin());
        values.put(NOI_DUNG, tin.getNoiDung());
        values.put(IMAGE, tin.getAnh());
        values.put(ID_TAI_KHOAN, tin.getID_TK());

        db.insert(TABLE_TINTUC,null,values);
        db.close();
        Log.e("Add tin tức : ","Thành công");
    }

    //Thêm đánh giá
    public void AddDanhGia(DanhGia danhgia){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOI_DUNG_DANH_GIA,danhgia.getNoiDungDanhGia());
        // values.put(ID_TAI_KHOAN,danhgia.getIDTK());
        values.put(TEN_TAI_KHOAN,danhgia.getTenTK());
        values.put(TEN_TINTUC, danhgia.getTenTin());
        db.insert(TABLE_DANH_GIA,null,values);
        db.close();
        Log.e("Add đánh giá : ","Thành công");
    }



    //Lấy tin mới nhất
    public Cursor getData1(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from "+TABLE_TINTUC+" ORDER BY "+ ID_TINTUC +" DESC LIMIT 4" , null );
        return res;
    }

    public Cursor getData4(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from "+TABLE_TINTUC+" ORDER BY "+ ID_TINTUC +" DESC LIMIT 7" , null );
        return res;
    }




    //Lấy tất cả truyện
    public Cursor getData2(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_TINTUC,null);
        return res;
    }

    //Lấy tất cả đánh giá
    public Cursor getData3(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_DANH_GIA,null);
        return res;
    }


    //Xóa truyện với id = i
    public int Delete(int i){
        SQLiteDatabase db = this.getReadableDatabase();

        int res = db.delete("tintuc", ID_TINTUC +" = "+i,null);
        return res;

    }


}
