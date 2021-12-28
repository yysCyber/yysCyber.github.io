<template>
    <div>
        <PageHeader page-title="Aerial Photography Calculator Help" back-page-title="Home"
                    :back-routes="['/', '/index', '/home']"/>

        <el-descriptions title="Basic Description of UAV Aerial Photography Calculator"/>

        <el-card>
            <span class="text item">
                This program is used to calculate the parameters involved in the low-altitude aerial photography of UAV.
            </span>
            <span class="text item">
                The calculation is based on the relevant mathematical formulas and the conclusions are for reference only.
            </span>
        </el-card>

        <el-divider/>

        <el-card>
            <div class="item text">
                Low-altitude aerial photography of UAV, the route like:
            </div>
            <div>
                <el-image style="width: 100px; height: 100px" :src="image1Src" :preview-src-list="image1PreviewSrc"/>
            </div>
            <div class="item text">
                Simply:
            </div>
            <div>
                <el-image style="width: 100px; height: 100px" :src="image2Src" :preview-src-list="image2PreviewSrc"/>
            </div>

        </el-card>

        <el-divider>
            <i class="el-icon-cpu"/>
        </el-divider>

        <el-descriptions title="Concepts in UAV Aerial Photography Calculator"/>

        <el-descriptions title="Parameters of UAV Camera"/>
        <el-row :gutter="10">
            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Image Size</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Image Sensor Size</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Focal Length</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <el-divider/>

        <el-row :gutter="10">
            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Pixel Size</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <el-divider>
            <i class="el-icon-cpu"/>
        </el-divider>

        <el-descriptions title="Parameters of UAV Aerial Photography"/>

        <el-row :gutter="10">
            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Flying Height</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Flying Speed</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <el-divider/>

        <el-row :gutter="10">
            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Longitudinal Overlap</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Lateral Overlap</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Ground Sampling Distance (GSD)</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <el-divider/>

        <el-row :gutter="10">
            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Actual Size of Aerial Photography</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Photography Distance</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>

            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Route Distance</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <el-divider/>

        <el-row :gutter="10">
            <el-col :span="8">
                <el-card>
                    <div slot="header" class="clearfix">
                        <span>Photo Time Interval</span>
                    </div>
                    <div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

    </div>
</template>

<script>
    import PageHeader from "../../components/PageHeader";

    export default {
        name: "Help",

        data() {
            return {
                image1Src: "/img/uav-ap/help/uav-ap-example-1.jpg",
                image1PreviewSrc: ["/img/uav-ap/help/uav-ap-example-1.jpg"],

                image2Src: "/img/uav-ap/help/uav-ap-example-2.jpg",
                image2PreviewSrc: ["/img/uav-ap/help/uav-ap-example-2.jpg"]
            }
        },

        components: {
            PageHeader
        }
    }
</script>

<style scoped>
    .text {
        font-size: 14px;
    }

    .item {
        padding: 18px 0;
    }

    .clearfix:before, .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }

    .box-card {
        /*width: 480px;*/
    }
</style>