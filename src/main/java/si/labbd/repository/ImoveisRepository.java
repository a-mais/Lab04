package si.labbd.repository;

import si.labbd.models.Imoveis;

public class ImoveisRepository extends BaseRepository<Imoveis> {
    public ImoveisRepository() {
        super(Imoveis.class);
    }
}
