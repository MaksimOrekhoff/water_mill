<template>
  <b-container style=" background: #0de555; text-align: center; justify-content: center;">
    <b-row>
      <b-col style="padding: 5%;">
        <ul class="tools">
          <li class="water">
            <b-badge variant="primary">0</b-badge>
          </li>
          <li class="txt">+</li>
          <li class="millet">
            <b-badge variant="warning">0</b-badge>
          </li>
          <li class="txt">=</li>
          <li class="flour">
            <b-badge variant="primary">0</b-badge>
          </li>
        </ul>
        <div>
          <b-img src="/anim.gif"></b-img>
        </div>
        <div>
          <h3>Water: 0</h3>
          <b-progress :value="3" :max="10" variant="primary" animated></b-progress>
          <h3>Energy: 0</h3>
          <b-progress :value="4" :max="10" animated variant="warning"></b-progress>
          <h3>Millet: 0</h3>
          <b-progress :value="5" :max="10" animated variant="info"></b-progress>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>


</script>

<style>
.tools {
  list-style: none;
  display: inline-flex;
}

.tools li {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border: #35495e 3px solid;
}

.tools li.txt {
  border: none;
  line-height: 80px;
  color: #fff;
  font-size: 200%;
}

.tools li.water {
  background: #fff url(/water.png) no-repeat center center/cover;
}

.tools li.millet {
  background: #fff url(/grain.png) no-repeat center center/cover;
}

.tools li.flour {
  background: #fff url(/flour_PNG20.png) no-repeat center center/cover;
}

</style>
