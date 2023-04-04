package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockDisplayWallMount;
import net.lepidodendron.entity.model.entity.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderDisplays extends TileEntitySpecialRenderer<BlockDisplayWallMount.TileEntityDisplayWallMount> {

    public static final ResourceLocation TEXTURE_APHNELEPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/aphnelepis.png");
    public static final ModelAphnelepis modelAphnelepis = new ModelAphnelepis();
    public static final ResourceLocation TEXTURE_AEGIROCASSIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/aegirocassis.png");
    public static final ModelAegirocassis modelAegirocassis = new ModelAegirocassis();
    public static final ResourceLocation TEXTURE_AETHEOLEPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/aetheolepis.png");
    public static final ModelAetheolepis modelAetheolepis = new ModelAetheolepis();
    public static final ResourceLocation TEXTURE_ANOMALOCARIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/anomalocaris.png");
    public static final ModelAnomalocaris modelAnomalocaris = new ModelAnomalocaris();
    public static final ResourceLocation TEXTURE_ARDUAFRONS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/arduafrons.png");
    public static final ModelArduafrons modelArduafrons = new ModelArduafrons();
    public static final ResourceLocation TEXTURE_ARTHROPLEURA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/arthropleura.png");
    public static final ModelArthropleura modelArthropleura = new ModelArthropleura();
    public static final ResourceLocation TEXTURE_ASPIDORHYNCHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/aspidorhynchus.png");
    public static final ModelAspidorhynchus modelAspidorhynchus = new ModelAspidorhynchus();
    public static final ResourceLocation TEXTURE_ASTERACANTHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/asteracanthus.png");
    public static final ModelAsteracanthus modelAsteracanthus = new ModelAsteracanthus();
    public static final ResourceLocation TEXTURE_BALHUTICARIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/balhuticaris.png");
    public static final ModelBalhuticaris modelBalhuticaris = new ModelBalhuticaris();
    public static final ResourceLocation TEXTURE_BESANOSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/besanosaurus.png");
    public static final ModelBesanosaurus modelBesanosaurus = new ModelBesanosaurus();
    public static final ResourceLocation TEXTURE_BRINDABELLASPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/brindabellaspis.png");
    public static final ModelBrindabellaspis modelBrindabellaspis = new ModelBrindabellaspis();
    public static final ResourceLocation TEXTURE_BUNDENBACHIELLUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/bundenbachiellus.png");
    public static final ModelBundenbachiellus modelBundenbachiellus = new ModelBundenbachiellus();
    public static final ResourceLocation TEXTURE_BUNGARTIUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/bungartius.png");
    public static final ModelBungartius modelBungartius = new ModelBungartius();
    public static final ResourceLocation TEXTURE_CAMPBELLODUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/campbellodus.png");
    public static final ModelCampbellodus modelCampbellodus = new ModelCampbellodus();
    public static final ResourceLocation TEXTURE_CAMUROPISCIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/camuropiscis.png");
    public static final ModelCamuropiscis modelCamuropiscis = new ModelCamuropiscis();
    public static final ResourceLocation TEXTURE_CERATIOCARIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ceratiocaris.png");
    public static final ModelCeratiocaris modelCeratiocaris = new ModelCeratiocaris();
    public static final ResourceLocation TEXTURE_CLIMATIUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/climatius.png");
    public static final ModelClimatius modelClimatius = new ModelClimatius();
    public static final ResourceLocation TEXTURE_COELOPHYSIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/coelophysis.png");
    public static final ModelCoelophysis modelCoelophysis = new ModelCoelophysis();
    public static final ResourceLocation TEXTURE_COMETICERCUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/cometicercus.png");
    public static final ModelCometicercus modelCometicercus = new ModelCometicercus();
    public static final ResourceLocation TEXTURE_CONCAVICARIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/concavicaris.png");
    public static final ModelConcavicaris modelConcavicaris = new ModelConcavicaris();
    public static final ResourceLocation TEXTURE_COWRALEPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/cowralepis.png");
    public static final ModelCowralepis modelCowralepis = new ModelCowralepis();
    public static final ResourceLocation TEXTURE_CTENURELLA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ctenurella.png");
    public static final ModelCtenurella modelCtenurella = new ModelCtenurella();
    public static final ResourceLocation TEXTURE_CYMBOSPONDYLUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/cymbospondylus.png");
    public static final ModelCymbospondylus modelCymbospondylus = new ModelCymbospondylus();
    public static final ResourceLocation TEXTURE_DAPEDIUM = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/dapedium.png");
    public static final ModelDapedium modelDapedium = new ModelDapedium();
    public static final ResourceLocation TEXTURE_DATHEOSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/datheosaurus.png");
    public static final ModelDatheosaurus modelDatheosaurus = new ModelDatheosaurus();
    public static final ResourceLocation TEXTURE_DELTOPTYCHIUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/deltoptychius.png");
    public static final ModelDeltoptychius modelDeltoptychius = new ModelDeltoptychius();
    public static final ResourceLocation TEXTURE_DESMATOSUCHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/desmatosuchus.png");
    public static final ModelDesmatosuchus modelDesmatosuchus = new ModelDesmatosuchus();
    public static final ResourceLocation TEXTURE_DIICTODON = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/diictodon.png");
    public static final ResourceLocation TEXTURE_EOSIMOPS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eosimops.png");
    public static final ResourceLocation TEXTURE_PROSICTODON = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/prosictodon.png");
    public static final ResourceLocation TEXTURE_ROBERTIA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/robertia.png");
    public static final ModelDiictodon modelDiictodon = new ModelDiictodon();
    public static final ResourceLocation TEXTURE_DIMETRODON = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/dimetrodon.png");
    public static final ModelDimetrodon modelDimetrodon = new ModelDimetrodon();
    public static final ResourceLocation TEXTURE_DIPLACANTHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/diplacanthus.png");
    public static final ModelDiplacanthus modelDiplacanthus = new ModelDiplacanthus();
    public static final ResourceLocation TEXTURE_DOLLOCARIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/dollocaris.png");
    public static final ModelDollocaris modelDollocaris = new ModelDollocaris();
    public static final ResourceLocation TEXTURE_DRACONICHTHYS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/draconichthys.png");
    public static final ModelDraconichthys modelDraconichthys = new ModelDraconichthys();
    public static final ResourceLocation TEXTURE_DRACOPRISTIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/dracopristis.png");
    public static final ModelDracopristis modelDracopristis = new ModelDracopristis();
    public static final ResourceLocation TEXTURE_DUNKLEOSTEUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/dunkleosteus.png");
    public static final ModelDunkleosteus modelDunkleosteus = new ModelDunkleosteus();
    public static final ResourceLocation TEXTURE_DUNYU = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/dunyu.png");
    public static final ModelDunyu modelDunyu = new ModelDunyu();
    public static final ResourceLocation TEXTURE_EASTMANOSTEUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eastmanosteus.png");
    public static final ModelEastmanosteus modelEastmanosteus = new ModelEastmanosteus();
    public static final ResourceLocation TEXTURE_EDAPHOSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/edaphosaurus.png");
    public static final ModelEdaphosaurus modelEdaphosaurus = new ModelEdaphosaurus();
    public static final ResourceLocation TEXTURE_ELGINIA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/elginia.png");
    public static final ModelElginia modelElginia = new ModelElginia();
    public static final ResourceLocation TEXTURE_ENDOTHIODON = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/endothiodon.png");
    public static final ModelEndothiodon modelEndothiodon = new ModelEndothiodon();
    public static final ResourceLocation TEXTURE_ENTELOGNATHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/entelognathus.png");
    public static final ModelEntelognathus modelEntelognathus = new ModelEntelognathus();
    public static final ResourceLocation TEXTURE_EORAPTOR = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eoraptor.png");
    public static final ModelEoraptor modelEoraptor = new ModelEoraptor();
    public static final ResourceLocation TEXTURE_EORHYNCHOCHELYS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eorhynchochelys.png");
    public static final ModelEorhynchochelys modelEorhynchochelys = new ModelEorhynchochelys();
    public static final ResourceLocation TEXTURE_ERYON = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eryon.png");
    public static final ModelEryon modelEryon = new ModelEryon();
    public static final ResourceLocation TEXTURE_ERYOPS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eryops.png");
    public static final ModelEryops modelEryops = new ModelEryops();
    public static final ResourceLocation TEXTURE_ERYTHROSUCHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/erythrosuchus.png");
    public static final ModelErythrosuchus modelErythrosuchus = new ModelErythrosuchus();
    public static final ResourceLocation TEXTURE_ESTEMMENOSUCHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/estemmenosuchus.png");
    public static final ModelEstemmenosuchus modelEstemmenosuchus = new ModelEstemmenosuchus();
    public static final ResourceLocation TEXTURE_EUCHAMBERSIA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/euchambersia.png");
    public static final ModelEuchambersia modelEuchambersia = new ModelEuchambersia();
    public static final ResourceLocation TEXTURE_EUGALEASPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eugaleaspis.png");
    public static final ModelEugaleaspis modelEugaleaspis = new ModelEugaleaspis();
    public static final ResourceLocation TEXTURE_FALLACOSTEUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/fallacosteus.png");
    public static final ModelFallacosteus modelFallacosteus = new ModelFallacosteus();
    public static final ResourceLocation TEXTURE_FOREYIA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/foreyia.png");
    public static final ModelForeyia modelForeyia = new ModelForeyia();
    public static final ResourceLocation TEXTURE_GEMUENDINA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/gemuendina.png");
    public static final ModelGemuendina modelGemuendina = new ModelGemuendina();
    public static final ResourceLocation TEXTURE_GOGONASUS= new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/gogonasus.png");
    public static final ModelGogonasus modelGogonasus = new ModelGogonasus();
    public static final ResourceLocation TEXTURE_GOODRADIGBEEON = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/goodradigbeeon.png");
    public static final ModelGoodradigbeeon modelGoodradigbeeon = new ModelGoodradigbeeon();
    public static final ResourceLocation TEXTURE_GOOLOOGONGIA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/gooloogongia.png");
    public static final ModelGooloogongia modelGooloogongia = new ModelGooloogongia();
    public static final ResourceLocation TEXTURE_GRIPHOGNATHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/griphognathus.png");
    public static final ModelGriphognathus modelGriphognathus = new ModelGriphognathus();
    public static final ResourceLocation TEXTURE_GYMNOTRACHELUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/gymnotrachelus.png");
    public static final ModelGymnotrachelus modelGymnotrachelus = new ModelGymnotrachelus();
    public static final ResourceLocation TEXTURE_HELICOPRION = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/helicoprion.png");
    public static final ModelHelicoprion modelHelicoprion = new ModelHelicoprion();
    public static final ResourceLocation TEXTURE_HENODUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/henodus.png");
    public static final ModelHenodus modelHenodus = new ModelHenodus();
    public static final ResourceLocation TEXTURE_INOSTRANCEVIA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/inostrancevia.png");
    public static final ModelInostrancevia modelInostrancevia = new ModelInostrancevia();
    public static final ResourceLocation TEXTURE_IVOITES = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ivoites.png");
    public static final ModelIvoites modelIvoites = new ModelIvoites();
    public static final ResourceLocation TEXTURE_KAIBABVENATOR = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/kaibabvenator.png");
    public static final ModelKaibabvenator modelKaibabvenator = new ModelKaibabvenator();
    public static final ResourceLocation TEXTURE_KUJDANOWIASPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/kujdanowiaspis.png");
    public static final ModelKujdanowiaspis modelKujdanowiaspis = new ModelKujdanowiaspis();
    public static final ResourceLocation TEXTURE_LASANIUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/lasanius.png");
    public static final ModelLasanius modelLasanius = new ModelLasanius();
    public static final ResourceLocation TEXTURE_LEPIDOTES = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/lepidotes.png");
    public static final ModelLepidotes modelLepidotes = new ModelLepidotes();
    public static final ResourceLocation TEXTURE_LEPTOLEPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/leptolepis.png");
    public static final ModelLeptolepis modelLeptolepis = new ModelLeptolepis();
    public static final ResourceLocation TEXTURE_LESSEMSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/lessemsaurus.png");
    public static final ModelLessemsaurus modelLessemsaurus = new ModelLessemsaurus();
    public static final ResourceLocation TEXTURE_LILIENSTERNUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/liliensternus.png");
    public static final ModelLiliensternus modelLiliensternus = new ModelLiliensternus();
    public static final ResourceLocation TEXTURE_LUNGMENSHANASPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/lungmenshanaspis.png");
    public static final ModelLungmenshanaspis modelLungmenshanaspis = new ModelLungmenshanaspis();
    public static final ResourceLocation TEXTURE_MACROMESODON = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/macromesodon.png");
    public static final ModelMacromesodon modelMacromesodon = new ModelMacromesodon();
    public static final ResourceLocation TEXTURE_MATERPISCIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/materpiscis_male.png");
    public static final ModelMaterpiscis modelMaterpiscis = new ModelMaterpiscis();
    public static final ResourceLocation TEXTURE_MEGALOGRAPTUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/megalograptus.png");
    public static final ModelMegalograptus modelMegalograptus = new ModelMegalograptus();
    public static final ResourceLocation TEXTURE_MEGALOSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/megalosaurus.png");
    public static final ModelMegalosaurus modelMegalosaurus = new ModelMegalosaurus();
    public static final ResourceLocation TEXTURE_MENASPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/menaspis.png");
    public static final ModelMenaspis modelMenaspis = new ModelMenaspis();
    public static final ResourceLocation TEXTURE_MICROBRACHIUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/microbrachius_male.png");
    public static final ModelMicrobrachius modelMicrobrachius = new ModelMicrobrachius();
    public static final ResourceLocation TEXTURE_MIGUASHAIA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/miguashaia.png");
    public static final ModelMiguashaia modelMiguashaia = new ModelMiguashaia();
    public static final ResourceLocation TEXTURE_MUENSTERELLA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/muensterella.png");
    public static final ModelMuensterella modelMuensterella = new ModelMuensterella();
    public static final ResourceLocation TEXTURE_MUSSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/mussaurus.png");
    public static final ModelMussaurus modelMussaurus = new ModelMussaurus();
    public static final ResourceLocation TEXTURE_NEEYAMBASPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/neeyambaspis.png");
    public static final ModelNeeyambaspis modelNeeyambaspis = new ModelNeeyambaspis();
    public static final ResourceLocation TEXTURE_NEREPISACANTHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/nerepisacanthus.png");
    public static final ModelNerepisacanthus modelNerepisacanthus = new ModelNerepisacanthus();
    public static final ResourceLocation TEXTURE_NOTHOSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/nothosaurus.png");
    public static final ModelNothosaurus modelNothosaurus = new ModelNothosaurus();
    public static final ResourceLocation TEXTURE_NOTIDANOIDES = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/notidanoides.png");
    public static final ModelNotidanoides modelNotidanoides = new ModelNotidanoides();
    public static final ResourceLocation TEXTURE_OMNIDENS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/omnidens.png");
    public static final ModelOmnidens modelOmnidens = new ModelOmnidens();
    public static final ResourceLocation TEXTURE_OPHTHALMOSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ophthalmosaurus.png");
    public static final ModelOphthalmosaurus modelOphthalmosaurus = new ModelOphthalmosaurus();
    public static final ResourceLocation TEXTURE_ORTHROZANCLUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/orthrozanclus.png");
    public static final ModelOrthrozanclus modelOrthrozanclus = new ModelOrthrozanclus();
    public static final ResourceLocation TEXTURE_OXYOSTEUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/oxyosteus.png");
    public static final ModelOxyosteus modelOxyosteus = new ModelOxyosteus();
    public static final ResourceLocation TEXTURE_PEDERPES = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/pederpes.png");
    public static final ModelPederpes modelPederpes = new ModelPederpes();
    public static final ResourceLocation TEXTURE_PHYLLOLEPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/phyllolepis.png");
    public static final ModelPhyllolepis modelPhyllolepis = new ModelPhyllolepis();
    public static final ResourceLocation TEXTURE_PIRANHAMESODON = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/piranhamesodon.png");
    public static final ModelPiranhamesodon modelPiranhamesodon = new ModelPiranhamesodon();
    public static final ResourceLocation TEXTURE_PLACERIAS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/placerias.png");
    public static final ModelPlacerias modelPlacerias = new ModelPlacerias();
    public static final ResourceLocation TEXTURE_POPOSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/poposaurus.png");
    public static final ModelPoposaurus modelPoposaurus = new ModelPoposaurus();
    public static final ResourceLocation TEXTURE_POSTOSUCHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/postosuchus.png");
    public static final ModelPostosuchus modelPostosuchus = new ModelPostosuchus();
    public static final ResourceLocation TEXTURE_PITURIASPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/pituriaspis.png");
    public static final ModelPituriaspis modelPituriaspis = new ModelPituriaspis();
    public static final ResourceLocation TEXTURE_PRIONOSUCHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/prionosuchus.png");
    public static final ModelPrionosuchus modelPrionosuchus = new ModelPrionosuchus();
    public static final ResourceLocation TEXTURE_PYGOPTERUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/pygopterus.png");
    public static final ModelPygopterus modelPygopterus = new ModelPygopterus();
    public static final ResourceLocation TEXTURE_QILINYU = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/qilinyu.png");
    public static final ModelQilinyu modelQilinyu = new ModelQilinyu();
    public static final ResourceLocation TEXTURE_REDFIELDIUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/redfieldius.png");
    public static final ModelRedfieldius modelRedfieldius = new ModelRedfieldius();
    public static final ResourceLocation TEXTURE_RHADINACANTHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/rhadinacanthus.png");
    public static final ModelRhadinacanthus modelRhadinacanthus = new ModelRhadinacanthus();
    public static final ResourceLocation TEXTURE_RHAMPHODOPSIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/rhamphodopsis.png");
    public static final ModelRhamphodopsis modelRhamphodopsis = new ModelRhamphodopsis();
    public static final ResourceLocation TEXTURE_ROLFOSTEUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/rolfosteus.png");
    public static final ModelRolfosteus modelRolfosteus = new ModelRolfosteus();
    public static final ResourceLocation TEXTURE_RUBIDGEA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/rubidgea.png");
    public static final ModelRubidgea modelRubidgea = new ModelRubidgea();
    public static final ResourceLocation TEXTURE_SAIVODUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/saivodus.png");
    public static final ModelSaivodus modelSaivodus = new ModelSaivodus();
    public static final ResourceLocation TEXTURE_SALTRIOVENATOR = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/saltriovenator.png");
    public static final ModelSaltriovenator modelSaltriovenator = new ModelSaltriovenator();
    public static final ResourceLocation TEXTURE_SAUROSUCHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/saurosuchus.png");
    public static final ModelSaurosuchus modelSaurosuchus = new ModelSaurosuchus();
    public static final ResourceLocation TEXTURE_SCHUGUROCARIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/schugurocaris.png");
    public static final ModelSchugurocaris modelSchugurocaris = new ModelSchugurocaris();
    public static final ResourceLocation TEXTURE_SHONISAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/shonisaurus.png");
    public static final ModelShonisaurus modelShonisaurus = new ModelShonisaurus();
    public static final ResourceLocation TEXTURE_SILUROLEPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/silurolepis.png");
    public static final ModelSilurolepis modelSilurolepis = new ModelSilurolepis();
    public static final ResourceLocation TEXTURE_SPHOOCERAS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/sphooceras.png");
    public static final ModelSphooceras modelSphooceras = new ModelSphooceras();
    public static final ResourceLocation TEXTURE_TAPINOCEPHALUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/tapinocephalus.png");
    public static final ModelTapinocephalus modelTapinocephalus = new ModelTapinocephalus();
    public static final ResourceLocation TEXTURE_TEGEOLEPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/tegeolepis.png");
    public static final ModelTegeolepis modelTegeolepis = new ModelTegeolepis();
    public static final ResourceLocation TEXTURE_THECODONTOSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/thecodontosaurus.png");
    public static final ModelThecodontosaurus modelThecodontosaurus = new ModelThecodontosaurus();
    public static final ResourceLocation TEXTURE_THRISSOPS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/thrissops.png");
    public static final ModelThrissops modelThrissops = new ModelThrissops();
    public static final ResourceLocation TEXTURE_TIKTAALIK = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/tiktaalik.png");
    public static final ModelTiktaalik modelTiktaalik = new ModelTiktaalik();
    public static final ResourceLocation TEXTURE_TITANICHTHYS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/titanichthys.png");
    public static final ModelTitanicthys modelTitanicthys = new ModelTitanicthys();
    public static final ResourceLocation TEXTURE_TURBOSCINETES = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/turboscinetes.png");
    public static final ModelTurboscinetes modelTurboscinetes = new ModelTurboscinetes();
    public static final ResourceLocation TEXTURE_TYRANNOPHONTES = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/tyrannophontes.png");
    public static final ModelTyrannophontes modelTyrannophontes = new ModelTyrannophontes();
    public static final ResourceLocation TEXTURE_UROSTHENES = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/urosthenes.png");
    public static final ModelUrosthenes modelUrosthenes = new ModelUrosthenes();
    public static final ResourceLocation TEXTURE_WARNETICARIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/warneticaris.png");
    public static final ModelWarneticaris modelWarneticaris = new ModelWarneticaris();
    public static final ResourceLocation TEXTURE_WUTTAGOONASPIS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/wuttagoonaspis.png");
    public static final ModelWuttagoonaspis modelWuttagoonaspis = new ModelWuttagoonaspis();
    public static final ResourceLocation TEXTURE_XINPUSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/xinpusaurus.png");
    public static final ModelXinpusaurus modelXinpusaurus = new ModelXinpusaurus();

    public static final ModelAkmonistion modelAkmonistion = new ModelAkmonistion();
    public static final ResourceLocation TEXTURE_AKMONISTION = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/akmonistion.png");

    public static final ModelFurca modelFurca = new ModelFurca();
    public static final ResourceLocation TEXTURE_FURCA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/furca.png");
    public static final ModelTomlinsonus modelTomlinsonus = new ModelTomlinsonus();
    public static final ResourceLocation TEXTURE_TOMLINSONUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/tomlinsonus.png");
    public static final ModelVachonisia modelVachonisia = new ModelVachonisia();
    public static final ResourceLocation TEXTURE_VACHONISIA = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/vachonisia.png");

    public static final ModelDryosaurus modelDryosaurus = new ModelDryosaurus();
    public static final ResourceLocation TEXTURE_DRYOSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/dryosaurus.png");
    public static final ModelDysaloTaxi modelDysalotosaurus = new ModelDysaloTaxi();
    public static final ResourceLocation TEXTURE_DYSALOTOSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/dysalotosaurus.png");
    public static final ModelStethacanthus modelStethacanthus = new ModelStethacanthus();
    public static final ResourceLocation TEXTURE_STETHACANTHUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/stethacanthus.png");

    public static final ModelKentrosaurus modelKentrosaurus = new ModelKentrosaurus();
    public static final ResourceLocation TEXTURE_KENTROSAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/kentrosaurus.png");
    public static final ModelSilesaurus modelSilesaurus = new ModelSilesaurus();
    public static final ResourceLocation TEXTURE_SILESAURUS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/silesaurus.png");
    public static final ModelPanguraptor modelPanguraptor = new ModelPanguraptor();
    public static final ResourceLocation TEXTURE_PANGURAPTOR = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/panguraptor.png");
    public static final ModelProganochelys modelProganochelys = new ModelProganochelys();
    public static final ResourceLocation TEXTURE_PROGANOCHELYS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/proganochelys.png");




    //public static final Modeltemplate modeltemplate = new Modeltemplate();
   //public static final ResourceLocation TEXTURE_TEMPLATE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/template.png");

}