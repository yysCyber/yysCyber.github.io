<template>
    <div>
        <PageHeader page-title="UAV Camera" back-page-title="Aerial Photography Calculator"
                    :back-routes="['/aerial-photography/calculator']"/>

        <el-row>
            <el-col :span="3" :offset="20">
                <el-button type="danger" @click="navigationToCameraAddPage">Add UAV Camera</el-button>
            </el-col>
        </el-row>

        <el-divider/>

        <el-empty v-if="uavCameraTableList.length === 0 || uavCameraTableList === null" description="No Data"/>

        <el-table v-else :data="uavCameraTableList" stripe v-loading="isLoading" element-loading-text="Loading..."
                  element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)">
            <el-table-column label="#" prop="index"/>
            <el-table-column label="Name" prop="name"/>
            <el-table-column label="Image Size(pixel×pixel)" prop="imageSize"/>
            <el-table-column label="Sensor Size(mm×mm)" prop="sensorSize"/>
            <el-table-column label="Pixel Size(μm)" prop="pixelSize"/>
            <el-table-column label="Focal Length(mm)" prop="focalLength"/>
            <el-table-column label="">
                <template slot-scope="scope">
                    <el-button type="text" size="medium" @click="detailUAVCameraParam(scope.row.index)">Detail
                    </el-button>
                    <el-button type="text" size="medium" @click="deleteUAVCameraParam(scope.row.uuid)">Delete
                    </el-button>
                </template>
            </el-table-column>
        </el-table>


        <el-dialog title="Detail" :visible.sync="dialogVisible">
            <el-descriptions class="margin-top" title="" :column="2" border>
                <el-descriptions-item :span="2">
                    <template slot="label">Name</template>
                    <span v-text="uavCameraCurrentSelected.uavCameraName"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="2">
                    <template slot="label">Created Time</template>
                    <span v-text="uavCameraCurrentSelected.uavCameraCreateTime"></span>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template slot="label">Image Length</template>
                    <span v-text="uavCameraCurrentSelected.uavCameraImageSizeLength"></span>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template slot="label">Image Width</template>
                    <span v-text="uavCameraCurrentSelected.uavCameraImageSizeWidth"></span>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template slot="label">Sensor Length</template>
                    <span v-text="uavCameraCurrentSelected.uavCameraSensorSizeLength"></span>
                </el-descriptions-item>

                <el-descriptions-item>
                    <template slot="label">Sensor Width</template>
                    <span v-text="uavCameraCurrentSelected.uavCameraSensorSizeWidth"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="2">
                    <template slot="label">Pixel Size</template>
                    <span v-text="uavCameraCurrentSelected.uavCameraPixelSize"></span>
                </el-descriptions-item>

                <el-descriptions-item :span="2">
                    <template slot="label">Focal Length</template>
                    <span v-text="uavCameraCurrentSelected.uavCameraFocalLength"></span>
                </el-descriptions-item>
            </el-descriptions>
        </el-dialog>


    </div>
</template>

<script>
    import PageHeader from "../../../components/PageHeader";

    import * as math from "mathjs";

    export default {
        name: "CameraInfo",

        data() {
            return {
                uavCameraTableList: [],

                uavCameraList: [],

                isLoading: false,

                dialogVisible: false,

                uavCameraCurrentSelected: {
                    uavCameraId: null,
                    uavCameraName: null,
                    uavCameraImageSizeLength: null,
                    uavCameraImageSizeWidth: null,
                    uavCameraSensorSizeLength: null,
                    uavCameraSensorSizeWidth: null,
                    uavCameraPixelSize: null,
                    uavCameraFocalLength: null,
                    uavCameraCreateTime: null
                }
            }
        },

        methods: {
            navigationToCameraAddPage() {
                if (this.$route.path !== '/aerial-photography/calculator/uav-camera/new') {
                    this.$router.push('/aerial-photography/calculator/uav-camera/new');
                }
            },

            detailUAVCameraParam(index) {
                this.uavCameraCurrentSelected.uavCameraName = null;
                this.uavCameraCurrentSelected.uavCameraCreateTime = null;
                this.uavCameraCurrentSelected.uavCameraImageSizeLength = null;
                this.uavCameraCurrentSelected.uavCameraImageSizeWidth = null;
                this.uavCameraCurrentSelected.uavCameraFocalLength = null;
                this.uavCameraCurrentSelected.uavCameraSensorSizeLength = null;
                this.uavCameraCurrentSelected.uavCameraSensorSizeWidth = null;
                this.uavCameraCurrentSelected.uavCameraPixelSize = null;

                index = index - 1;
                this.uavCameraCurrentSelected.uavCameraName = this.uavCameraList[index].uavCameraName;
                this.uavCameraCurrentSelected.uavCameraCreateTime = this.uavCameraList[index].uavCameraCreateTime;
                this.uavCameraCurrentSelected.uavCameraImageSizeLength = this.uavCameraList[index].uavCameraImageSizeLength + " pixels";
                this.uavCameraCurrentSelected.uavCameraImageSizeWidth = this.uavCameraList[index].uavCameraImageSizeWidth + " pixels";
                this.uavCameraCurrentSelected.uavCameraFocalLength = this.uavCameraList[index].uavCameraFocalLength + " mm";

                if (this.uavCameraList[index].uavCameraPixelSize === null) {
                    this.uavCameraCurrentSelected.uavCameraSensorSizeLength = this.uavCameraList[index].uavCameraSensorSizeLength + " mm";
                    this.uavCameraCurrentSelected.uavCameraSensorSizeWidth = this.uavCameraList[index].uavCameraSensorSizeWidth + " mm";
                    this.uavCameraCurrentSelected.uavCameraPixelSize = math.round(math.divide(math.bignumber(this.uavCameraList[index].uavCameraSensorSizeLength + ""), math.bignumber(this.uavCameraList[index].uavCameraImageSizeLength + "")) * 1000, 4) + " μm (Calculated)";
                } else {
                    this.uavCameraCurrentSelected.uavCameraPixelSize = this.uavCameraList[index].uavCameraPixelSize + " μm";
                    this.uavCameraCurrentSelected.uavCameraSensorSizeLength = math.round(this.uavCameraList[index].uavCameraPixelSize * this.uavCameraList[index].uavCameraImageSizeLength / 1000, 0) + " mm (Calculated)";
                    this.uavCameraCurrentSelected.uavCameraSensorSizeWidth = math.round(this.uavCameraList[index].uavCameraPixelSize * this.uavCameraList[index].uavCameraImageSizeWidth / 1000, 0) + " mm (Calculated)";
                }

                this.dialogVisible = true;
            },

            deleteUAVCameraParam(uuid) {
                const url = this.$TP_UAV_URL_PREFIX + "/aerial-photography/uav-camera/remove/" + uuid;
                const that = this;

                this.$confirm('Remove the data?', 'Warning', {
                    confirmButtonText: 'Yes',
                    cancelButtonText: 'No',
                    type: 'warning'
                }).then(() => {
                    that.isLoading = true;

                    that.$axios.get(url).then((response) => {
                        that.isLoading = false;

                        if (response.data.state) {
                            if (response.data.code === "200") {
                                location.reload();
                            }
                        }
                    }).catch(() => {
                    });
                }).catch(() => {
                });
            }
        },

        created() {
            const url = this.$TP_UAV_URL_PREFIX + "/aerial-photography/uav-camera/all";
            const that = this;

            this.isLoading = true;

            this.$axios.get(url).then((response) => {
                that.isLoading = false;

                if (response.data.state) {
                    if (response.data.code === "200") {
                        let arr = response.data.data;

                        if (arr.length === 0) {
                            that.uavCameraTableList = [];
                            that.uavCameraList = [];
                        } else {
                            for (let i = 0; i < arr.length; i++) {
                                let obj1 = {};
                                let obj2 = {};

                                obj1.uavCameraId = arr[i].cpUuid;
                                obj1.uavCameraName = arr[i].cpName;
                                obj1.uavCameraImageSizeLength = arr[i].cpImageSizeLength;
                                obj1.uavCameraImageSizeWidth = arr[i].cpImageSizeWidth;
                                obj1.uavCameraSensorSizeLength = arr[i].cpImageSensorSizeLength;
                                obj1.uavCameraSensorSizeWidth = arr[i].cpImageSensorSizeWidth;
                                obj1.uavCameraPixelSize = arr[i].cpPixelSize;
                                obj1.uavCameraFocalLength = arr[i].cpFocalLength;
                                obj1.uavCameraCreateTime = arr[i].cpCreateTime;

                                that.uavCameraList.push(obj1);

                                obj2.index = i + 1;
                                obj2.uuid = arr[i].cpUuid;
                                obj2.name = arr[i].cpName;
                                obj2.imageSize = arr[i].cpImageSizeLength + "×" + arr[i].cpImageSizeWidth;
                                obj2.focalLength = arr[i].cpFocalLength;

                                if (arr[i].cpPixelSize === null) {
                                    obj2.sensorSize = arr[i].cpImageSensorSizeLength + "×" + arr[i].cpImageSensorSizeWidth;
                                    obj2.pixelSize = math.round(math.divide(math.bignumber(arr[i].cpImageSensorSizeLength + ""), math.bignumber(arr[i].cpImageSizeLength + "")) * 1000, 4);
                                } else {
                                    obj2.pixelSize = arr[i].cpPixelSize;
                                    obj2.sensorSize = math.round(arr[i].cpPixelSize * arr[i].cpImageSizeLength / 1000, 0) + "×" + math.round(arr[i].cpPixelSize * arr[i].cpImageSizeWidth / 1000, 0);
                                }

                                that.uavCameraTableList.push(obj2);
                            }
                        }
                    }
                }
            }).catch(() => {
            });
        },

        components: {
            PageHeader
        }
    }
</script>

<style scoped>

</style>