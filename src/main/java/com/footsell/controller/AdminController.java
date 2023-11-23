package com.footsell.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.footsell.domain.CategoryVO;
import com.footsell.domain.GoodsVO;
import com.footsell.domain.GoodsViewVO;
import com.footsell.domain.OrderListVO;
import com.footsell.domain.OrderVO;
import com.footsell.service.AdminService;
import net.sf.json.JSONArray;
import utils.UploadFileUtils;

@Controller
@RequestMapping({"/admin/*"})
public class AdminController {
  private static final Logger Logger = LoggerFactory.getLogger(AdminController.class);

  @Inject
  AdminService adminService;

  @Resource(name = "uploadPath")
  private String uploadPath;

  @RequestMapping(value = {"/index"}, method = {RequestMethod.GET})
  public void getIndex() throws Exception {
    Logger.info("get index");
  }

  @RequestMapping(value = {"/goods/register"}, method = {RequestMethod.GET})
  public void getGoodsRegister(Model model) throws Exception {
    Logger.info("get goods register");
    List<CategoryVO> category = null;
    category = this.adminService.category();
    model.addAttribute("category", JSONArray.fromObject(category));
  }

  @RequestMapping(value = {"/goods/register"}, method = {RequestMethod.POST})
  public String postGoodsRegister(@ModelAttribute("vo") GoodsVO vo,
      @RequestParam("file") MultipartFile file) throws Exception {
    String imgUploadPath = String.valueOf(this.uploadPath) + File.separator + "imgUpload";
    String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
    String fileName = null;
    if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
      fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(),
          file.getBytes(), ymdPath);
      vo.setGdsImg(
          String.valueOf(File.separator) + "imgUpload" + ymdPath + File.separator + fileName);
      vo.setGdsThumbImg(String.valueOf(File.separator) + "imgUpload" + ymdPath + File.separator
          + "s" + File.separator + "s_" + fileName);
    } else {
      fileName = String.valueOf(File.separator) + "images" + File.separator + "none.png";
      vo.setGdsImg(fileName);
      vo.setGdsThumbImg(fileName);
    }
    this.adminService.register(vo);
    return "redirect:/admin/index";
  }

  @RequestMapping(value = {"/goods/list"}, method = {RequestMethod.GET})
  public void getGoodsList(Model model) throws Exception {
    Logger.info("get goods list");
    List<GoodsViewVO> list = this.adminService.goodslist();
    model.addAttribute("list", list);
  }

  @RequestMapping(value = {"/goods/view"}, method = {RequestMethod.GET})
  public void getGoodsview(@RequestParam("n") int gdsNum, Model model) throws Exception {
    Logger.info("get goods view");
    GoodsViewVO goods = this.adminService.goodsView(gdsNum);
    model.addAttribute("goods", goods);
  }

  @RequestMapping(value = {"/goods/modify"}, method = {RequestMethod.GET})
  public void getGoodsModify(@RequestParam("n") int gdsNum, Model model) throws Exception {
    Logger.info("get goods modify");
    GoodsViewVO goods = this.adminService.goodsView(gdsNum);
    model.addAttribute("goods", goods);
    List<CategoryVO> category = null;
    category = this.adminService.category();
    model.addAttribute("category", JSONArray.fromObject(category));
  }

  @RequestMapping(value = {"/goods/modify"}, method = {RequestMethod.POST})
  public String postGoodsModify(GoodsVO vo, MultipartFile file, HttpServletRequest req)
      throws Exception {
    Logger.info("post goods modify");
    if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
      (new File(String.valueOf(this.uploadPath) + req.getParameter("gdsImg"))).delete();
      (new File(String.valueOf(this.uploadPath) + req.getParameter("gdsThumbImg"))).delete();
      String imgUploadPath = String.valueOf(this.uploadPath) + File.separator + "imgUpload";
      String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
      String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(),
          file.getBytes(), ymdPath);
      vo.setGdsImg(
          String.valueOf(File.separator) + "imgUpload" + ymdPath + File.separator + fileName);
      vo.setGdsThumbImg(String.valueOf(File.separator) + "imgUpload" + ymdPath + File.separator
          + "s" + File.separator + "s_" + fileName);
    } else {
      vo.setGdsImg(req.getParameter("gdsImg"));
      vo.setGdsThumbImg(req.getParameter("gdsThumbImg"));
    }
    this.adminService.goodsModify(vo);
    return "redirect:/admin/index";
  }

  @RequestMapping(value = {"/goods/delete"}, method = {RequestMethod.POST})
  public String postGoodsDelete(@RequestParam("n") int gdsNum) throws Exception {
    Logger.info("post goods delete");
    this.adminService.goodsDelete(gdsNum);
    return "redirect:/admin/goods/list";
  }

  @RequestMapping(value = {"/shop/orderList"}, method = {RequestMethod.GET})
  public void getOrderList(Model model) throws Exception {
    Logger.info("get order list");
    List<OrderVO> orderList = this.adminService.orderList();
    model.addAttribute("orderList", orderList);
  }

  @RequestMapping(value = {"/shop/orderView"}, method = {RequestMethod.GET})
  public void getOrderList(@RequestParam("n") String orderId, OrderVO order, Model model)
      throws Exception {
    Logger.info("get order view");
    order.setOrderId(orderId);
    List<OrderListVO> orderView = this.adminService.orderView(order);
    model.addAttribute("orderView", orderView);
  }

  @RequestMapping(value = {"/shop/orderView"}, method = {RequestMethod.POST})
  public String delivery(OrderVO order) throws Exception {
    Logger.info("post order view");
    this.adminService.delivery(order);
    return "redirect:/admin/shop/orderView?n=" + order.getOrderId();
  }



  //ck 에디터에서 파일 업로드
  @RestController
  public class CKEditorImageUploadController {

    @RequestMapping(value = "/admin/goods/ckUpload", method = RequestMethod.POST)
    public ResponseEntity<?> postCKEditorImgUpload(@RequestParam("upload") MultipartFile upload) {
      try {
        // 랜덤 문자 생성
        String uid = UUID.randomUUID().toString();

        String fileName = upload.getOriginalFilename(); // 파일 이름 가져오기
        byte[] bytes = upload.getBytes();

        // 업로드 경로
        String ckUploadPath =
            uploadPath + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;

        try (OutputStream out = new FileOutputStream(new File(ckUploadPath))) {
          out.write(bytes);
        } catch (IOException e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업로드 실패");
        }

        String fileUrl = "/ckUpload/" + uid + "_" + fileName;

        // CKEditor로 JSON 응답 보내기
        Map<String, String> response = new HashMap<>();
        response.put("uploaded", "1");
        response.put("fileName", fileName);
        response.put("url", fileUrl);

        return ResponseEntity.ok(response);
      } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업로드 실패");
      }
    }
  }
}
