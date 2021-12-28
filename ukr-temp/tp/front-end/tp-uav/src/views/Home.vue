<template>
    <div>
        <el-carousel :interval="5000" type="card" autoplay>
            <el-carousel-item v-for="(item, index) in carouselImage">
                <img :src="item" :alt="index" class="image"/>
            </el-carousel-item>
        </el-carousel>

        <el-divider/>

        <!-- TODO 附加功能 日历+备忘录 -->
        <el-row :gutter="20">
            <el-col :span="12">
                <el-calendar v-model="todayDate"></el-calendar>
            </el-col>

            <el-col :span="12">
                <el-descriptions title="Note"/>
            </el-col>
        </el-row>
    </div>
</template>

<script>

    export default {
        name: 'Home',
        data() {
            return {
                carouselImage: ['/img/home/1.png',
                    '/img/home/2.png',
                    '/img/home/3.png',
                    '/img/home/5.png'],

                todayDate: new Date()
            }
        }
    }
</script>

<style scoped>
    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

    .image {
        width: 100%;
        height: 100%;
    }
</style>
