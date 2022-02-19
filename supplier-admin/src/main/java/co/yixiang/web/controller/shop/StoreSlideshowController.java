package co.yixiang.web.controller.shop;

import java.util.List;

import co.yixiang.common.utils.RegexUtils;
import co.yixiang.shop.domain.StoreSlideshow;
import co.yixiang.shop.service.IStoreSlideshowService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import co.yixiang.common.annotation.Log;
import co.yixiang.common.enums.BusinessType;
import co.yixiang.common.core.controller.BaseController;
import co.yixiang.common.core.domain.AjaxResult;
import co.yixiang.common.utils.poi.ExcelUtil;
import co.yixiang.common.core.page.TableDataInfo;

/**
 * 轮播图Controller
 *
 * @author ruoyi
 * @date 2022-02-03
 */
@Controller
@RequestMapping("/shop/slideshow")
public class StoreSlideshowController extends BaseController
{
    private String prefix = "shop/slideshow";

    @Autowired
    private IStoreSlideshowService storeSlideshowService;

    @RequiresPermissions("shop:slideshow:view")
    @GetMapping()
    public String slideshow()
    {
        return prefix + "/slideshow";
    }

    /**
     * 查询轮播图列表
     */
    @RequiresPermissions("shop:slideshow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StoreSlideshow storeSlideshow)
    {
        startPage();
        List<StoreSlideshow> list = storeSlideshowService.selectStoreSlideshowList(storeSlideshow);
        return getDataTable(list);
    }

    /**
     * 导出轮播图列表
     */
    @RequiresPermissions("shop:slideshow:export")
    @Log(title = "轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StoreSlideshow storeSlideshow)
    {
        List<StoreSlideshow> list = storeSlideshowService.selectStoreSlideshowList(storeSlideshow);
        ExcelUtil<StoreSlideshow> util = new ExcelUtil<StoreSlideshow>(StoreSlideshow.class);
        return util.exportExcel(list, "轮播图数据");
    }

    /**
     * 新增轮播图
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存轮播图
     */
    @RequiresPermissions("shop:slideshow:add")
    @Log(title = "轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StoreSlideshow storeSlideshow)
    {
        if (RegexUtils.isNumeric(storeSlideshow.getSlideshowSort())){
            return new AjaxResult(AjaxResult.Type.ERROR, "轮播图分类排序请输入数字", null);
        }
        return toAjax(storeSlideshowService.insertStoreSlideshow(storeSlideshow));
    }

    /**
     * 修改轮播图
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        StoreSlideshow storeSlideshow = storeSlideshowService.selectStoreSlideshowById(id);
        mmap.put("storeSlideshow", storeSlideshow);
        return prefix + "/edit";
    }

    /**
     * 修改保存轮播图
     */
    @RequiresPermissions("shop:slideshow:edit")
    @Log(title = "轮播图", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StoreSlideshow storeSlideshow)
    {
        return toAjax(storeSlideshowService.updateStoreSlideshow(storeSlideshow));
    }

    /**
     * 删除轮播图
     */
    @RequiresPermissions("shop:slideshow:remove")
    @Log(title = "轮播图", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storeSlideshowService.deleteStoreSlideshowByIds(ids));
    }
}
