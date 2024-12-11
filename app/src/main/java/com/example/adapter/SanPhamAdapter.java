package com.example.adapter; // Khai báo package cho Adapter.

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab06.R; // Import resource layout.
import com.example.model.SanPham; // Import mô hình dữ liệu `SanPham`.

import java.util.List; // Dùng danh sách để truyền dữ liệu vào Adapter.

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    // Adapter kế thừa từ ArrayAdapter để kết nối danh sách sản phẩm với ListView.

    public SanPhamAdapter(Context context, List<SanPham> sanPhams) {
        super(context, 0, sanPhams);
        // Gọi superclass để gán danh sách dữ liệu và thiết lập Adapter.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Phương thức này được gọi mỗi khi một mục trong ListView cần hiển thị.

        if (convertView == null) {
            // Kiểm tra nếu convertView là null, nếu đúng thì tạo mới.
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            // Dùng LayoutInflater để nạp layout `list_item.xml` cho từng mục trong ListView.
        }

        SanPham sanPham = getItem(position);
        // Lấy thông tin sản phẩm tại vị trí `position` trong danh sách dữ liệu.

        TextView txtSanPham = convertView.findViewById(R.id.txtSanPham);
        // Lấy tham chiếu đến TextView trong layout `list_item.xml`.

        txtSanPham.setText(sanPham.getMa()+": "+sanPham.getTen() + " - Giá: " + sanPham.getGia());
        // Gán thông tin tên và giá sản phẩm từ đối tượng `SanPham` vào TextView.

        return convertView;
        // Trả về View đã được thiết lập để hiển thị trong ListView.
    }
}

