# Viết tài liệu sử dụng Markdown
* Markdown là ngôn ngữ đánh dấu văn bản (kiểu như HTML) được sử dụng trên GitHub.
* Cú pháp của Markdown rất đơn giản, có thể xem trên [Wikipedia](http://en.wikipedia.org/wiki/Markdown) hoặc trên [trang chủ của dự án](http://daringfireball.net/projects/markdown/) hoặc [cheatsheet của mashery.com](http://support.mashery.com/docs/customizing_your_portal/Markdown_Cheat_Sheet)
* Để soạn thảo tài liệu Markdown chỉ cần sử dụng một trình soạn thảo như *Sublime Text 2* hoặc *Notepad++*

# Làm việc với Git
## Clone Project về máy (chỉ cần làm một lần)
	$ git clone git@github.com:heroandtn3/faceme.git

* Nên clone vào trong workspace của Eclipse
* Mở Eclipse lên và import project vừa clone vào. Lưu ý bỏ chọn mục *Copy project to workspace*.

## Chỉnh sửa, lập trình trên Eclipse như bình thường
### Commit thay đổi
Khi nào cần commit thay đổi thì di chuyển vào thư mục chứa project và gõ:

	$ git status # để kiểm trang tình trạng hiện tại của project
	$ git add file1 file2 # để add thêm các file mới hoặc add các file đã sửa vào stage area
	$ git commit -m "mô tả về lần commit này" # tiến hành commit dựa trên những gì đã có ở stage area

### Tải lên server 
Khi cảm thấy chương trình đã ổn, commit xong và gõ:

	$ git push

### Cập nhật từ server về 
Đôi khi phải thực hiện lệnh này trước khi tải lên server

	$ git pull

### Xử lý xung đột
	$ git status # kiểm tra xem những file nào đang bị xung đột
	$ # mở file đó lên và xử lý xung đột bằng tay
	$ git add filex # thông báo với git là filex đã được xử lý xung đột xong
	$ git commit -m "mô tả" # commit

### Sử dụng branch 
Xem trong sách ProGit

